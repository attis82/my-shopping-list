package com.farkasatesz.myshoppinglist.models.unitType

import com.farkasatesz.myshoppinglist.models.BaseEntity
import kotlinx.serialization.Serializable

@Serializable
data class UnitType(
    override var entityId: String? = null,
    override var entityName: String = ""
): BaseEntity(){
    override fun toEntityMap(): Map<String, Any?> {
        return mapOf(
            "entityId" to entityId,
            "entityName" to entityName
        )
    }
}
