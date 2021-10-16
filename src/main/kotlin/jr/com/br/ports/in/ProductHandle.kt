package jr.com.br.ports.`in`

import jr.com.br.model.ProductModel
import java.util.UUID

interface ProductHandle {
    suspend fun insert(productModel: ProductModel): ProductModel

    suspend fun delete(id: UUID): Int

    suspend fun getById(id: UUID): ProductModel

    suspend fun listAllPaged(limit: Int, offset: Long): List<ProductModel>
}