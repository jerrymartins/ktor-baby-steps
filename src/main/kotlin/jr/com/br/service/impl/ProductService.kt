package jr.com.br.service.impl

import jr.com.br.config.DatabaseFactory.dbQuery
import jr.com.br.entity.Product
import jr.com.br.model.ProductModel
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.*

class ProductService {

    suspend fun productInsert() = dbQuery {
        SchemaUtils.create(Product)

        toProductModel(Product.insert {
            it[id] = UUID.randomUUID()
            it[name] = getRandomString(30)
            it[price] = 5.5
        })
    }

    private fun toProductModel(row: InsertStatement<Number>) = ProductModel(
        id = row[Product.id],
        name = row[Product.name],
        price = row[Product.price]
    )

    private fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

}