package mx.utng.alp.smarthealthmonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Warning
import mx.utng.alp.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import mx.utng.alp.smarthealthmonitor.navigation.SmartHealthNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartHealthNavGraph()
        }
    }
}


@Composable
fun SmartHealthScreen(
    isLandscape: Boolean,
    nombre: String = "Juan García",
    frecuenciaCardiaca: Int = 78,
    pasos: Int = 4250
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (isLandscape) {
                LandscapeLayout(nombre, frecuenciaCardiaca, pasos)
            } else {
                PortraitLayout(nombre, frecuenciaCardiaca, pasos)
            }
        }
    }
}

@Composable
fun PortraitLayout(nombre: String, frecuenciaCardiaca: Int, pasos: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(32.dp))
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary // Usa tu naranja
            )
            Text(text = nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(32.dp))

            // Frecuencia Cardíaca
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Favorite, null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "$frecuenciaCardiaca bpm", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error)
            }

            // Pasos
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DirectionsWalk, null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "%,d pasos".format(pasos), fontSize = 20.sp)
            }
        }

        // Botón de Alerta
        Button(
            onClick = { /* Acción */ },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Default.Warning, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("ENVIAR ALERTA")
        }
    }
}

@Composable
fun LandscapeLayout(nombre: String, frecuenciaCardiaca: Int, pasos: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Lado izquierdo: Usuario
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Person, null, modifier = Modifier.size(60.dp), tint = MaterialTheme.colorScheme.primary)
                Text(text = nombre, fontWeight = FontWeight.Bold)
            }
            // Lado derecho: Datos
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Favorite, null, tint = MaterialTheme.colorScheme.error)
                    Text(text = " $frecuenciaCardiaca bpm", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DirectionsWalk, null, tint = MaterialTheme.colorScheme.primary)
                    Text(text = " %,d pasos".format(pasos))
                }
            }
        }

        Button(
            onClick = { /* Acción */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("ENVIAR ALERTA")
        }
    }
}

// Previews para que veas el cambio sin correr el emulador
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PortraitPreview() {
    SmartHealthMonitorTheme { SmartHealthScreen(isLandscape = false) }
}

@Preview(showBackground = true, device = "spec:width=891dp,height=411dp,orientation=landscape")
@Composable
fun LandscapePreview() {
    SmartHealthMonitorTheme { SmartHealthScreen(isLandscape = true) }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThemePreview() {
    SmartHealthMonitorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "SmartHealth Monitor",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}