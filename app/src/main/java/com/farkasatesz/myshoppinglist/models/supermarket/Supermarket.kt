package com.farkasatesz.myshoppinglist.models.supermarket

import com.farkasatesz.myshoppinglist.models.BaseEntity
import kotlinx.serialization.Serializable

@Serializable
data class Supermarket(
    override var entityId: String? = null,
    override var entityName: String = "",
    var location: String = ""
): BaseEntity(){
    override fun toEntityMap(): Map<String, Any?> {
        return mapOf(
            "entityId" to entityId,
            "entityName" to entityName,
            "location" to location
        )
    }
}
