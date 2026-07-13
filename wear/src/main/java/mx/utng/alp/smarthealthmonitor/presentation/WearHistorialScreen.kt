package mx.utng.alp.smarthealthmonitor.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.lazy.*
import androidx.wear.compose.foundation.rotary.*
import androidx.wear.compose.material.*
import kotlinx.coroutines.flow.StateFlow
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC

@Composable
fun WearHistorialScreen(
    onBack: () -> Unit,
    viewModel: WearDashboardViewModel = viewModel()
) {
    val historial by viewModel.historial.collectAsState()
    val listState = rememberScalingLazyListState()
    val focusRequester = remember { FocusRequester() }

    // Pedir foco para recibir eventos de la corona
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        timeText = {
            TimeText(modifier = Modifier.scrollAway(listState))
        },
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        }
    ) {
        ScalingLazyColumn(
            state    = listState,
            modifier = Modifier
                .fillMaxSize()
                .rotaryScrollable(  // ← conecta la corona
                    behavior = RotaryScrollableDefaults.behavior(
                        scrollableState = listState
                    ),
                    focusRequester = focusRequester
                )
        ) {
            item {
                Text("Historial (${historial.size})",
                     style = MaterialTheme.typography.title3,
                     modifier = Modifier.padding(8.dp))
            }
            if (historial.isEmpty()) {
                item {
                    Text("Sin lecturas aún",
                         style = MaterialTheme.typography.body2,
                         modifier = Modifier.padding(8.dp))
                }
            } else {
                items(historial, key = { it.id }) { lectura ->
                    WearFilaHistorial(lectura = lectura)
                }
            }
        }
    }
}
