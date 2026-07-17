package mx.utng.smarthealthmonitor.tv.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.utng.smarthealthmonitor.tv.domain.model.LecturaFC

object SmartHealthRepository {
    private val _fcActual = MutableStateFlow(78)
    val fcActual: StateFlow<Int> = _fcActual.asStateFlow()

    fun obtenerHistorial(): Flow<List<LecturaFC>> {
        return kotlinx.coroutines.flow.flowOf(listOf(
            LecturaFC(1, 78), LecturaFC(2, 82), LecturaFC(3, 75)
        ))
    }
}
