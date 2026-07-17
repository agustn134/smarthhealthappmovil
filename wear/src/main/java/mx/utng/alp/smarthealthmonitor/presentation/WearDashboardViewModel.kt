package mx.utng.alp.smarthealthmonitor.presentation
 
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mx.utng.alp.smarthealthmonitor.data.SmartHealthRepository
import mx.utng.alp.smarthealthmonitor.wear.mqtt.MqttWearPublisher
 
class WearDashboardViewModel(application: Application) : AndroidViewModel(application) {
    val fc: StateFlow<Int> = SmartHealthRepository.fcFlow
        .map { if (it == 0) 72 else it } // Valor por defecto
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 72)
 
    val historial: StateFlow<List<mx.utng.alp.smarthealthmonitor.data.models.LecturaFC>> =
        SmartHealthRepository.obtenerHistorial()
            .stateIn(viewModelScope,
                     SharingStarted.WhileSubscribed(5_000),
                     emptyList())
                     
    private val mqttPublisher = MqttWearPublisher(application)
    
    init {
        mqttPublisher.connect()
        viewModelScope.launch {
            fc.collect { bpm ->
                val estado = when { bpm < 60 -> "FC Baja"; bpm > 100 -> "FC Alta"; else -> "Normal" }
                mqttPublisher.publishFC(bpm, estado)
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        mqttPublisher.disconnect()
    }
}
