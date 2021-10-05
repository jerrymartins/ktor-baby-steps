package jr.com.br.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import jr.com.br.model.City
import jr.com.br.model.cities
import jr.com.br.service.impl.ProductService
import kotlinx.coroutines.runBlocking
import org.kodein.di.instance
import org.kodein.di.ktor.di


fun Route.cityRouting() {
    //val productService by di().instance<ProductService>()
    val productService = ProductService()

    route("/products") {
        get {

//            runBlocking {
//                val id = productService.productInsert()
//                print(id)
//            }

            if (cities.isNotEmpty()) {
                call.respond(productService.productInsert())
            } else {
                call.respondText("No products found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val city =
                cities.find { it.id == id.toLong() } ?: return@get call.respondText(
                    "No customer with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(city)
        }
        post {
            val city = call.receive<City>()
            // TODO - This shouldn't really be done in production as
            // we should be accessing a mutable list in a thread-safe manner.
            // However, in production code we wouldn't be using mutable lists as a database!
            cities.add(city)
            call.respondText("City stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (cities.removeIf { it.id == id.toLong() }) {
                call.respondText("City removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}


fun Application.registerCityRoutes() {
    routing {
        cityRouting()
    }
}


