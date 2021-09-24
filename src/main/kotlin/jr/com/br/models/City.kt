package jr.com.br.models

import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class City(val id: Long, val name: String, val cityData: List<CityData>?)

val cities = mutableListOf(
    City(1, "campo grande", null),
    City(2, "belém", null),
    City(3, "manaus", null),
    City(4, "campinas", listOf(CityData(1, 4, 400, 250.9, "2021-09-21"))),
    City(5, "itacoatiara", null),
    City(6, "paulínia", null),
)

