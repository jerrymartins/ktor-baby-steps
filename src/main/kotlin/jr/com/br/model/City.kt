package jr.com.br.model

import kotlinx.serialization.Serializable

@Serializable
data class City(val id: Long, val name: String)

val cities = mutableListOf(
    City(1, "campo grande"),
    City(2, "belém"),
    City(3, "manaus"),
    City(4, "campinas"),
    City(5, "itacoatiara"),
    City(6, "paulínia"),
)

