package jr.com.br.model

import jr.com.br.config.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ProductModel(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val price: Double)

