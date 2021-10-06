package jr.com.br.service.impl

import jr.com.br.config.bind.repositoryBind
import jr.com.br.model.ProductModel
import jr.com.br.repository.ProductPort
import jr.com.br.service.ProductHandle
import org.kodein.di.instance
import java.util.UUID

class ProductService: ProductHandle {
    private val productRepository: ProductPort by repositoryBind.di.instance()

    override suspend fun insert(productModel: ProductModel) = productRepository.insert(productModel)

    override suspend fun delete(id: UUID) = productRepository.delete(id)

    override suspend fun getById(id: UUID) = productRepository.getById(id)

    override suspend fun listAllPaged(limit: Int, offset: Long) = productRepository.listAllPaged(limit, offset)

}