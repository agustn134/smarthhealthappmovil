package mx.utng.alp.smarthealthmonitor.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC

@Dao
interface LecturaFCDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(lectura: LecturaFC)

    @Query("""
        SELECT * FROM historial_fc 
        ORDER BY fecha DESC 
        LIMIT 50
    """)
    fun obtenerUltimas(): Flow<List<LecturaFC>>

    @Query("SELECT COUNT(*) FROM historial_fc")
    suspend fun contarRegistros(): Int

    @Query("""
        DELETE FROM historial_fc WHERE 
        fecha < :limite
    """)
    suspend fun limpiarViejos(limite: Long)
}