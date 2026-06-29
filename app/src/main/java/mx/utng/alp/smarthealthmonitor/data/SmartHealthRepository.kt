package mx.utng.alp.smarthealthmonitor.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import mx.utng.alp.smarthealthmonitor.data.db.LecturaFCDao
import mx.utng.alp.smarthealthmonitor.data.db.SmartHealthDB
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC

object SmartHealthRepository {
    private val _fcFlow = MutableStateFlow(0)
    val fcFlow: StateFlow<Int> = _fcFlow.asStateFlow()

    private var dao: LecturaFCDao? = null

    fun init(context: Context) {
        dao = SmartHealthDB.getDatabase(context).lecturaDao()
    }

    suspend fun actualizarFC(bpm: Int) {
        _fcFlow.value = bpm
        dao?.insertar(LecturaFC(bpm = bpm))
    }

    fun obtenerHistorial(): Flow<List<LecturaFC>> = dao?.obtenerUltimas() ?: emptyFlow()
}