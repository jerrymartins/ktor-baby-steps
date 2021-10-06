package jr.com.br.ports.out

import jr.com.br.model.ProductModel
import java.util.UUID

interface ProductPort {
    suspend fun insert(productModel: ProductModel): UUID

    suspend fun delete(id: UUID): Int

    suspend fun getById(id: UUID): ProductModel

    suspend fun listAllPaged(limit: Int, offset: Long): List<ProductModel>
}