package com.farkasatesz.myshoppinglist.models.category

import com.farkasatesz.myshoppinglist.models.BaseEntity
import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    override var entityId: String? = null,
    override var entityName: String = ""
): BaseEntity() {
    override fun toEntityMap(): Map<String, Any?> {
        return mapOf(
            "entityId" to entityId,
            "entityName" to entityName
        )
    }
}

