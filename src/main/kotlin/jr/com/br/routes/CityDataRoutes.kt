package jr.com.br.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import jr.com.br.models.citiesData

fun Route.listCitiesDataRoute() {
    get("/cities-data") {
        if (citiesData.isNotEmpty()) {
            call.respond(citiesData)
        }
    }
}

fun Route.getCitiesDataRoute() {
    get("/cities-data/{id}") {
        val id = (call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)) as Long
        val cityData = citiesData.find { it.id == id } ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )
        call.respond(cityData)
    }
}

 fun Application.registerCitiesDataRoutes() {
    routing {
        listCitiesDataRoute()
        getCitiesDataRoute()
    }
}