package jr.com.br

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json
import io.ktor.server.netty.EngineMain
import jr.com.br.kafka.consumeMessages
import jr.com.br.kafka.createConsumer
import jr.com.br.route.registerCityRoutes

fun main(args: Array<String>): Unit {
    createConsumer().consumeMessages("ECOMMERCE_NEW_ORDER")
    return EngineMain.main(args)
}


fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCityRoutes()
}
