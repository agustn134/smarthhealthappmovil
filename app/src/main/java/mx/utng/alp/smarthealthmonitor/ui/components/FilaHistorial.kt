package mx.utng.alp.smarthealthmonitor.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FilaHistorial(lectura: LecturaFC) {
    val formatoHora = SimpleDateFormat("HH:mm", Locale.getDefault())
    val horaTexto = formatoHora.format(Date(lectura.fecha))

    val esNormal = lectura.bpm in 60..100

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${lectura.bpm} bpm",
            color = if (esNormal) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = horaTexto,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}