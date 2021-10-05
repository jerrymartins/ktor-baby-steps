package jr.com.br.service

import jr.com.br.service.impl.ProductService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices() {
    bind<ProductService>() with singleton { ProductService() }
}