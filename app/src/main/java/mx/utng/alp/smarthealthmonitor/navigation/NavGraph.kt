package mx.utng.alp.smarthealthmonitor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.utng.alp.smarthealthmonitor.LoginScreen
import mx.utng.alp.smarthealthmonitor.ui.screens.DashboardScreen

@Composable
fun SmartHealthNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onHistorialClick = { /* navController.navigate(Screen.Historial.route) */ },
                onAlertClick = { /* navController.navigate(Screen.Alerta.route) */ }
            )
        }
        // Puedes agregar las demás pantallas conforme las implementes
    }
}
