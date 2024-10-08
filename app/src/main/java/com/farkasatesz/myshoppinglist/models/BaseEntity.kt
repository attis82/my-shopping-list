package com.farkasatesz.myshoppinglist.models

abstract class BaseEntity {
    abstract var entityId: String?
    abstract var entityName: String
    abstract fun toEntityMap(): Map<String, Any?>
}