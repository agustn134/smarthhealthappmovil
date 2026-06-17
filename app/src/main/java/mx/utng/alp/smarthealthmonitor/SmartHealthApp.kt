package mx.utng.alp.smarthealthmonitor

import android.app.Application
import mx.utng.alp.smarthealthmonitor.data.SmartHealthRepository

class SmartHealthApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SmartHealthRepository.init(this)
    }
}