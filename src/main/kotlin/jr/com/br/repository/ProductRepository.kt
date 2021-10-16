package jr.com.br.repository

import jr.com.br.config.DatabaseFactory
import jr.com.br.entity.Product
import jr.com.br.model.ProductModel
import jr.com.br.ports.out.ProductPort
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.UUID

object ProductRepository: ProductPort {
    override suspend fun insert(productModel: ProductModel) = DatabaseFactory.dbQuery {
        SchemaUtils.create(Product)

        val inserted = Product.insert {
            it[id] = UUID.randomUUID()
            it[name] = productModel.name
            it[price] = productModel.price
        }

        toProductModel(inserted)
    }

    override suspend fun delete(id: UUID) = DatabaseFactory.dbQuery {
        Product.deleteWhere { Product.id eq id }
    }

    override suspend fun getById(id: UUID)= DatabaseFactory.dbQuery {
        toProductModel(Product.select { Product.id eq id }.first())
    }

    override suspend fun listAllPaged(limit: Int, offset: Long) = DatabaseFactory.dbQuery {
        Product.selectAll().limit(limit, offset).map { toProductModel(it) }
    }

    private fun toProductModel(row: ResultRow) = ProductModel(
        id = row[Product.id],
        name = row[Product.name],
        price = row[Product.price]
    )

    private fun toProductModel(row: InsertStatement<Number>) = ProductModel(
        id = row[Product.id],
        name = row[Product.name],
        price = row[Product.price]
    )
}