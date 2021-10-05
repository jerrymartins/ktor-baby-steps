package jr.com.br.service

import jr.com.br.service.impl.ProductService
import org.kodein.di.*

val kodein = DI {
    bindSingleton { ProductService() }
}
