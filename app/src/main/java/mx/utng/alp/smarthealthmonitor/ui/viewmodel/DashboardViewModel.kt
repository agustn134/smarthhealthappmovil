package mx.utng.alp.smarthealthmonitor.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mx.utng.alp.smarthealthmonitor.data.SmartHealthRepository
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC
import mx.utng.alp.smarthealthmonitor.data.models.MockData

class DashboardViewModel : ViewModel() {
    val fc: StateFlow<Int> = SmartHealthRepository.fcFlow
        .map { if (it == 0) MockData.fcActual else it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), MockData.fcActual)

    val historial: StateFlow<List<LecturaFC>> = SmartHealthRepository.obtenerHistorial()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}