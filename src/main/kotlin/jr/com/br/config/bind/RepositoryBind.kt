package jr.com.br.config.bind

import jr.com.br.repository.ProductPort
import jr.com.br.repository.ProductRepository
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val repositoryBind = DI {
    bindSingleton<ProductPort> { ProductRepository }
}
