package jr.com.br.service.impl

import jr.com.br.config.DatabaseFactory.dbQuery
import jr.com.br.entity.Product
import jr.com.br.model.ProductModel
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.ResultRow
import java.util.UUID

class ProductService {

    suspend fun insert(productModel: ProductModel) = dbQuery {
        SchemaUtils.create(Product)

        Product.insert {
            it[id] = UUID.randomUUID()
            it[name] = productModel.name
            it[price] = productModel.price
        } get Product.id
    }

    suspend fun delete(id: UUID) = dbQuery {
        Product.deleteWhere { Product.id eq id }
    }

    suspend fun getById(id: UUID)= dbQuery {
        Product.select { Product.id eq id }.mapNotNull { toProductModel(it) }
    }

    suspend fun listAllPaged(limit: Int, offset: Long) = dbQuery {
        Product.selectAll().limit(limit, offset).map { toProductModel(it) }
    }
    
    private fun toProductModel(row: ResultRow) = ProductModel(
        id = row[Product.id],
        name = row[Product.name],
        price = row[Product.price]
    )

}