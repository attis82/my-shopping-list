package com.farkasatesz.myshoppinglist.models.product

import com.farkasatesz.myshoppinglist.models.BaseEntity
import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    override var entityId: String? = null,
    override var entityName: String = "",
    @Contextual var categoryRef: DocumentReference? = null,
    @Contextual var unitTypeRef: DocumentReference? = null,
    @Contextual var supermarketRef: DocumentReference? = null,
    var price: Double = 0.0,
    var quantity: Double = 0.0
): BaseEntity(){
    override fun toEntityMap(): Map<String, Any?> {
        return mapOf(
            "entityId" to entityId,
            "entityName" to entityName,
            "category" to categoryRef,
            "unitType" to unitTypeRef,
            "supermarket" to supermarketRef,
            "price" to price,
            "quantity" to quantity
        )
    }
}
