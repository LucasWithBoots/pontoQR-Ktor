package LucasWithBoots.github.io.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val id: Int,
    val nome: String,
    val email: String,
    val ehcriador: Boolean,
    val data_criacao: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)