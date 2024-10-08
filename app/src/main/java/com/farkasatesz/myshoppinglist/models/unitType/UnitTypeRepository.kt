package com.farkasatesz.myshoppinglist.models.unitType

import com.farkasatesz.myshoppinglist.firebase.fireStore.unitType.UnitTypeImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository

class UnitTypeRepository(private val unitTypeImpl: UnitTypeImpl) : BaseRepository<UnitType>(unitTypeImpl) {
    suspend fun checkExistence(name: String) = unitTypeImpl.checkExistence(name)
}