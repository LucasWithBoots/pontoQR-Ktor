package LucasWithBoots.github.io.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable

@Serializable
data class Qrcodigo(
    val id: Int? = null,
    val id_usuariocriador: Int,
    val titulo: String,
    val descricao: String,
    val data_criacao: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    var ativo: Boolean = true
)