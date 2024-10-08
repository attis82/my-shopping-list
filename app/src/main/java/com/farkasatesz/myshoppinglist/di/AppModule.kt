package com.farkasatesz.myshoppinglist.di

import com.farkasatesz.myshoppinglist.firebase.fireStore.category.CategoryImpl
import com.farkasatesz.myshoppinglist.firebase.fireStore.unitType.UnitTypeImpl
import com.farkasatesz.myshoppinglist.models.category.CategoryRepository
import com.farkasatesz.myshoppinglist.models.category.CategoryViewModel
import com.farkasatesz.myshoppinglist.models.unitType.UnitTypeRepository
import com.farkasatesz.myshoppinglist.models.unitType.UnitTypeViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseFirestore.getInstance() }

    single { CategoryImpl(get()) }
    single { CategoryRepository(get()) }
    viewModel { CategoryViewModel(get()) }

    single { UnitTypeImpl(get()) }
    single { UnitTypeRepository(get()) }
    viewModel { UnitTypeViewModel(get()) }

}