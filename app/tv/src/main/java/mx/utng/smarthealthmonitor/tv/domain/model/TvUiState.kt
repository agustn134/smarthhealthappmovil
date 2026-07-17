package mx.utng.smarthealthmonitor.tv.domain.model
import mx.utng.smarthealthmonitor.tv.domain.model.LecturaFC
 
data class TvUiState(
    val lecturas    : List<LecturaFC> = emptyList(),
    val fcActual    : Int             = 0,
    val fcEstado    : String          = "Desconocido",
    val ultimaHora  : String          = "--:--:--",
    val isLoading   : Boolean         = true,
    val error       : String?         = null,
)
