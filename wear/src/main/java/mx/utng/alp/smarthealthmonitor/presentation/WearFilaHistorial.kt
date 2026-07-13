package mx.utng.alp.smarthealthmonitor.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.*
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC

@Composable
fun WearFilaHistorial(lectura: LecturaFC) {
    val color = if (lectura.bpm in 60..100)
        MaterialTheme.colors.primary
    else
        MaterialTheme.colors.error

    val sdf = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
    val horaStr = sdf.format(java.util.Date(lectura.fecha))

    Chip(
        label = { Text("${lectura.bpm} bpm",
                       color = color) },
        secondaryLabel = { Text(horaStr) },
        onClick = { },
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.fillMaxWidth()
    )
}
