package mx.utng.alp.smarthealthmonitor.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mx.utng.alp.smarthealthmonitor.data.SmartHealthRepository

class WearDashboardViewModel : ViewModel() {
    val fc: StateFlow<Int> = SmartHealthRepository.fcFlow
        .map { if (it == 0) 72 else it } // Valor por defecto
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 72)
}
