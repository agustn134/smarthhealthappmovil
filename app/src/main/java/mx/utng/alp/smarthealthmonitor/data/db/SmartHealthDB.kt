package mx.utng.alp.smarthealthmonitor.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC

@Database(
    entities = [LecturaFC::class],
    version = 1,
    exportSchema = false // Se usa en true para migraciones en producción
)
abstract class SmartHealthDB : RoomDatabase() {

    // Conectamos el DAO que creamos en el paso anterior
    abstract fun lecturaDao(): LecturaFCDao

    companion object {
        @Volatile
        private var INSTANCE: SmartHealthDB? = null

        fun getDatabase(context: Context): SmartHealthDB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SmartHealthDB::class.java,
                    "smarthealthmonitor_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}