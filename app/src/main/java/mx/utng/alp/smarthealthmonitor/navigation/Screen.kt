package mx.utng.alp.smarthealthmonitor.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC
import mx.utng.alp.smarthealthmonitor.data.models.MockData
import mx.utng.alp.smarthealthmonitor.ui.components.FilaHistorial
import mx.utng.alp.smarthealthmonitor.ui.components.TarjetaDato
import mx.utng.alp.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme

sealed class Screen(val route: String) {
    object Login     : Screen("login")
    object Dashboard : Screen("dashboard")
    object Historial : Screen("historial")
    object Alerta    : Screen("alerta")
}
