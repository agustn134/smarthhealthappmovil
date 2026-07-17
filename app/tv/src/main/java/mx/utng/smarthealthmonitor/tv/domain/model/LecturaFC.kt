package mx.utng.smarthealthmonitor.tv.domain.model

data class LecturaFC(
    val id: Int = 0,
    val bpm: Int,
    val fecha: Long = System.currentTimeMillis()
)
