package mx.utng.alp.smarthealthmonitor.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.utng.alp.smarthealthmonitor.LoginScreen
import mx.utng.alp.smarthealthmonitor.ui.screens.DashboardScreen
import mx.utng.alp.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartHealthNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // ── Login ──────────────────────────────────────
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // ── Dashboard ──────────────────────────────────
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                // Aquí ya quitamos los comentarios para que naveguen
                onHistorialClick = { navController.navigate(Screen.Historial.route) },
                onAlertClick = { navController.navigate(Screen.Alerta.route) }
            )
        }

        // ── Historial (Pantallas agregadas de nuevo) ───
        composable(Screen.Historial.route) {
            PantallaEnConstruccion(
                titulo = "Historial completo",
                onBack = { navController.popBackStack() }
            )
        }

        // ── Alerta ─────────────────────────────────────
        composable(Screen.Alerta.route) {
            PantallaEnConstruccion(
                titulo = "Enviar alerta",
                onBack = { navController.popBackStack() }
            )
        }
    }
}

// ── Función temporal recuperada ────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEnConstruccion(titulo: String, onBack: () -> Unit) {
    SmartHealthMonitorTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(titulo) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }) { pad ->
            Box(Modifier.fillMaxSize().padding(pad), contentAlignment = Alignment.Center) {
                Text("Próximamente: $titulo", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}