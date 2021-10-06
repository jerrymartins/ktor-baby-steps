package jr.com.br.config.bind

import jr.com.br.ports.`in`.ProductHandle
import jr.com.br.service.impl.ProductService
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val serviceBind = DI {
    bindSingleton<ProductHandle> { ProductService() }
}
