package com.farkasatesz.myshoppinglist.firebase.fireStore.unitType

interface UnitTypeCrud {
    suspend fun checkExistence(name: String): Boolean
}