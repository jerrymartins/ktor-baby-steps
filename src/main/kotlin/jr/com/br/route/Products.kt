package jr.com.br.route

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.delete
import jr.com.br.config.bind.serviceBind
import jr.com.br.model.ProductModel
import jr.com.br.service.ProductHandle
import jr.com.br.service.impl.ProductService
import org.kodein.di.instance
import java.util.UUID


fun Route.productRouting() {
    val productService: ProductHandle by serviceBind.di.instance()

    route("/products") {
        get("paged/{limit}/{offset}") {
            val limit = call.parameters["limit"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val offset = call.parameters["offset"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            call.respond(productService.listAllPaged(limit.toInt(), offset.toLong()))
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val product = productService.getById(UUID.fromString(id))

            if (product.name.isNotEmpty()) {
                call.respondText(text = "Product not found", status = HttpStatusCode.NotFound)
            } else {
                call.respond(product)
            }
        }

        post {
            val product = call.receive<ProductModel>()
            productService.insert(product)
            call.respondText(text = "Product stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

            call.respond(status = HttpStatusCode.OK, productService.delete(UUID.fromString(id)))
        }
    }
}


fun Application.registerCityRoutes() {
    routing {
        productRouting()
    }
}


