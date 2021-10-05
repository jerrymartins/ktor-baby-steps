package jr.com.br.service

import jr.com.br.service.impl.ProductService
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val kodein = DI {
    bindSingleton { ProductService() }
}
