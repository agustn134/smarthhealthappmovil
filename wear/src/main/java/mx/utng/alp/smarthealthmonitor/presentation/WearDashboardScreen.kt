package mx.utng.alp.smarthealthmonitor.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import mx.utng.alp.smarthealthmonitor.presentation.components.WearFCCard

@Composable
fun WearDashboardScreen(
    onAlertClick: () -> Unit = {},
    onHistorialClick: () -> Unit = {},
    viewModel: WearDashboardViewModel = viewModel()
) {
    val fc by viewModel.fc.collectAsState()
    val listState = rememberScalingLazyListState()

    Scaffold(
        timeText = { TimeText(modifier = Modifier.scrollAway(listState)) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    ) {
        ScalingLazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            item { WearFCCard(fc = fc, modifier = Modifier.fillMaxWidth()) }
            item {
                Chip(
                    label = { Text("⚠ Alerta") },
                    onClick = onAlertClick,
                    colors = ChipDefaults.primaryChipColors(backgroundColor = MaterialTheme.colors.error),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Chip(
                    label = { Text("📋 Historial") },
                    onClick = onHistorialClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
