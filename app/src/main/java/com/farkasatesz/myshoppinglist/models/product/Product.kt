package com.farkasatesz.myshoppinglist.models.product

import com.farkasatesz.myshoppinglist.models.BaseEntity
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    override var entityId: String? = null,
    override var entityName: String = "",
    var categoryName: String = "",
    var unitTypeName: String = "",
    var supermarketName: String = "",
    var price: Double = 0.0,
    var quantity: Double = 0.0
): BaseEntity(){
    override fun toEntityMap(): Map<String, Any?> {
        return mapOf(
            "entityId" to entityId,
            "entityName" to entityName,
            "category" to categoryName,
            "unitType" to unitTypeName,
            "supermarket" to supermarketName,
            "price" to price,
            "quantity" to quantity
        )
    }
}
