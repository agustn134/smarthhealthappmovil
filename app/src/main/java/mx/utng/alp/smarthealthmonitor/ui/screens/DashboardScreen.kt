package mx.utng.alp.smarthealthmonitor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.utng.alp.smarthealthmonitor.data.models.MockData
import mx.utng.alp.smarthealthmonitor.ui.components.FilaHistorial
import mx.utng.alp.smarthealthmonitor.ui.components.TarjetaDato
import mx.utng.alp.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onHistorialClick: () -> Unit = {},
    onAlertClick: () -> Unit = {}
) {
    var fc by remember { mutableIntStateOf(MockData.fcActual) }
    var pasos by remember { mutableIntStateOf(MockData.pasosActual) }
    val historial = MockData.historialFC

    var mostrarAlerta by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    SmartHealthMonitorTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "SmartHealth",
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            fc = (60..130).random()
                            pasos += (10..50).random()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Simular datos Wearable"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor    = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick       = { mostrarAlerta = true },
                    containerColor = MaterialTheme.colorScheme.error
                ) {
                    Icon(
                        imageVector       = Icons.Default.Warning,
                        contentDescription = "Enviar alerta de emergencia",
                        tint             = MaterialTheme.colorScheme.onError
                    )
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding       = PaddingValues(16.dp),
                verticalArrangement   = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    TarjetaDato(
                        valor      = "$fc",
                        unidad     = "bpm",
                        label      = "Frecuencia cardíaca",
                        colorValor = if (fc > 100) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                }
                item {
                    TarjetaDato(
                        valor      = "%,d".format(pasos),
                        unidad     = "pasos",
                        label      = "Pasos del día",
                        colorValor = MaterialTheme.colorScheme.primary
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ) {
                        Text("Historial reciente", style = MaterialTheme.typography.titleMedium)
                        TextButton(onClick = onHistorialClick) {
                            Text("Ver todo")
                        }
                    }
                }
                items(historial, key = { it.id }) { lectura ->
                    FilaHistorial(lectura = lectura)
                }
            }
        }
    }

    if (mostrarAlerta) {
        AlertaScreen(
            fc = fc,
            onDismiss = { mostrarAlerta = false },
            onConfirmar = {
                scope.launch {
                    mostrarAlerta = false
                    snackbarHostState.showSnackbar(
                        message = "Alerta de emergencia enviada",
                        actionLabel = "OK"
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true, name = "Dashboard - Light", showSystemUi = true)
@Composable
private fun DashboardScreenPreview() {
    SmartHealthMonitorTheme {
        DashboardScreen()
    }
}