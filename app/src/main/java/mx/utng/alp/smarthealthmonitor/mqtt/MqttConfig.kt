package mx.utng.alp.smarthealthmonitor.mqtt

import mx.utng.alp.smarthealthmonitor.BuildConfig

object MqttConfig {
    const val BROKER_URL = BuildConfig.MQTT_BROKER_URL
    const val USERNAME = BuildConfig.MQTT_USERNAME
    const val PASSWORD = BuildConfig.MQTT_PASSWORD

    // Topics UTNG
    const val TOPIC_FC = "utng/smarthealthmonitor/fc"
    const val TOPIC_TV = "utng/smarthealthmonitor/tv"
    const val TOPIC_ALERTA = "utng/smarthealthmonitor/alerta"
}
