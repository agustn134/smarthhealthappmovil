package mx.utng.alp.smarthealthmonitor.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*

@Composable
fun WearAlertaScreen(
    onConfirmar: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("¿Enviar alerta?", style = MaterialTheme.typography.title2)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onConfirmar,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
        ) {
            Text("SÍ")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onBack,
            colors = ButtonDefaults.secondaryButtonColors()
        ) {
            Text("No")
        }
    }
}
