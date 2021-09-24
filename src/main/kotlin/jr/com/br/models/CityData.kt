package jr.com.br.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class CityData(val id: Long, val cityId: Long, val totalAttendances: Int, val value: Double , val date: String)

val citiesData = listOf(
    CityData(1, 1, 200, 2500.6, Date().toString()),
    CityData(2, 2, 500, 2500.6, Date().toString()),
    CityData(3, 3, 700, 2500.6, Date().toString()),
    CityData(4, 4, 100, 2500.6, Date().toString()),
    CityData(5, 5, 600, 2500.6, Date().toString()),
    CityData(6, 6, 900, 2500.6, Date().toString()),
)