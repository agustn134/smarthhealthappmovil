package mx.utng.smarthealthmonitor.tv.domain.model
import mx.utng.alp.smarthealthmonitor.data.models.LecturaFC
 
data class TvUiState(
    val lecturas    : List<LecturaFC> = emptyList(),
    val fcActual    : Int             = 0,
    val isLoading   : Boolean         = true,
    val error       : String?         = null,
)
