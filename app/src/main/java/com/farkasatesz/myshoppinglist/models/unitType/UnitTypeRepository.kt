package com.farkasatesz.myshoppinglist.models.unitType

import com.farkasatesz.myshoppinglist.firebase.fireStore.unitType.UnitTypeImpl
import com.farkasatesz.myshoppinglist.models.BaseRepository
import com.google.firebase.firestore.DocumentReference

class UnitTypeRepository(private val unitTypeImpl: UnitTypeImpl) : BaseRepository<UnitType>(unitTypeImpl) {
    suspend fun checkExistence(name: String) = unitTypeImpl.checkExistence(name)
    suspend fun getUnitTypeReference(unitTypeId: String) = unitTypeImpl.getUnitTypeReference(unitTypeId)
    suspend fun getUnitTypeFromReference(unitTypeRef: DocumentReference) = unitTypeImpl.getUnitTypeFromReference(unitTypeRef)
}