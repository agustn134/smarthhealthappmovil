package mx.utng.alp.smarthealthmonitor

import android.content.Context
import com.google.android.gms.wearable.Wearable

class WearDataSender(context: Context) {
    private val messageClient = Wearable.getMessageClient(context)
    private val nodeClient = Wearable.getNodeClient(context)

    fun enviarFC(bpm: Int) {
        nodeClient.connectedNodes.addOnSuccessListener { nodes ->
            for (node in nodes) {
                messageClient.sendMessage(node.id, "/sensor/fc", bpm.toString().toByteArray())
            }
        }
    }
}