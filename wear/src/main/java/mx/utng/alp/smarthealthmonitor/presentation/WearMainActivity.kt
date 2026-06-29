package mx.utng.alp.smarthealthmonitor.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mx.utng.alp.smarthealthmonitor.HealthDataService
import mx.utng.alp.smarthealthmonitor.presentation.theme.SmartHealthWearTheme

class WearMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartHealthWearTheme {
                // Por ahora lo dejamos así, en el Ejercicio 02 pondremos el NavGraph
                WearDashboardScreen()
            }

            // Solicitud de permiso para registrar el sensor
            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    lifecycleScope.launch {
                        HealthDataService.registrar(this@WearMainActivity)
                    }
                }
            }
            LaunchedEffect(Unit) {
                permissionLauncher.launch(Manifest.permission.BODY_SENSORS)
            }
        }
    }
}
