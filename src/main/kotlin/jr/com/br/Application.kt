package jr.com.br

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json
import io.ktor.server.netty.EngineMain
import jr.com.br.routes.registerCustomerRoutes
import jr.com.br.routes.registerOrderRoutes

fun main(args: Array<String>): Unit = EngineMain.main(args)


fun Application.module(testing: Boolean = true) {
    install(ContentNegotiation) {
        json()
    }
    registerCustomerRoutes()
    registerOrderRoutes()
}
