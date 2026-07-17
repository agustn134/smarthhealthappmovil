package mx.utng.alp.smarthealthmonitor

import android.app.Application
import mx.utng.alp.smarthealthmonitor.data.SmartHealthRepository
import mx.utng.alp.smarthealthmonitor.mqtt.MqttAppService
import mx.utng.alp.smarthealthmonitor.data.sync.NeonSyncWorker

class SmartHealthApp : Application() {
    lateinit var mqttService: MqttAppService

    override fun onCreate() {
        super.onCreate()
        SmartHealthRepository.init(this)
        
        // Inicializar MQTT con el StateFlow del Repository
        mqttService = MqttAppService(
            context = this,
            fcFlow  = SmartHealthRepository.fcFlow
        )
        mqttService.connect()

        // Programar sync periódico con Neon 
        NeonSyncWorker.schedule(this)
    }
}