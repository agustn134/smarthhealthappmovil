package mx.utng.smarthealthmonitor.tv.presentation
import androidx.lifecycle.ViewModel; import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*; import kotlinx.coroutines.launch
import mx.utng.smarthealthmonitor.tv.domain.repository.SmartHealthRepository
import mx.utng.smarthealthmonitor.tv.domain.model.TvUiState
 
import android.content.Context
import mx.utng.alp.smarthealthmonitor.mqtt.TvMessage
import mx.utng.smarthealthmonitor.tv.mqtt.MqttTvSubscriber
 
class TvViewModel(
    private val repository: SmartHealthRepository,
    private val context: Context
) : ViewModel() {
 
    private val _state = MutableStateFlow(TvUiState())
    val state: StateFlow<TvUiState> = _state.asStateFlow()
 
    // Flow de mensajes MQTT entrantes
    private val mqttFlow = MutableStateFlow<TvMessage?>(null)
    private val mqttSubscriber = MqttTvSubscriber(context, mqttFlow)
 
    init {
        // Observar historial reactivo del Room DAO
        viewModelScope.launch {
            repository.obtenerHistorial()
                .catch { e -> _state.update{it.copy(error=e.message,isLoading=false)} }
                .collect { lecturas ->
                    _state.update { it.copy(lecturas=lecturas, isLoading=false) }
                }
        }
        // Observar FC actual (StateFlow del sensor)
        viewModelScope.launch {
            repository.fcActual.collect { bpm ->
                _state.update { it.copy(fcActual = bpm) }
            }
        }
        
        mqttSubscriber.connect()
 
        // Observar mensajes MQTT y actualizar el estado de la UI
        viewModelScope.launch {
            mqttFlow.collect { tvMsg ->
                tvMsg ?: return@collect
                _state.update { it.copy(
                    fcActual = tvMsg.bpm,
                    fcEstado = tvMsg.estado,
                    ultimaHora = tvMsg.hora,
                    isLoading = false
                )}
            }
        }
    }
 
    override fun onCleared() {
        super.onCleared()
        mqttSubscriber.disconnect()
    }
}
