package jr.com.br.entity

import org.jetbrains.exposed.sql.Table

object Product: Table() {
    val id = uuid("id_product")
    val name = varchar("name", length = 50)
    val price = double("price")
    override val primaryKey = PrimaryKey(id, name = "PK_Product_ID")
}