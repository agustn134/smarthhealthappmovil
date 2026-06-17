package mx.utng.alp.smarthealthmonitor.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "historial_fc")
data class LecturaFC(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bpm: Int,
    val fecha: Long = System.currentTimeMillis()
)


object MockData {
    val historialFC = listOf(
        LecturaFC(id = 1, bpm = 78),
        LecturaFC(id = 2, bpm = 82),
        LecturaFC(id = 3, bpm = 76),
        LecturaFC(id = 4, bpm = 110),
        LecturaFC(id = 5, bpm = 71),
        LecturaFC(id = 6, bpm = 80),
        LecturaFC(id = 7, bpm = 74)
    )
    var fcActual = 78
    var pasosActual = 4250
}