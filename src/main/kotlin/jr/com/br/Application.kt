package jr.com.br

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json
import io.ktor.server.netty.EngineMain
import jr.com.br.route.registerCityRoutes

fun main(args: Array<String>): Unit = EngineMain.main(args)


fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCityRoutes()
}
