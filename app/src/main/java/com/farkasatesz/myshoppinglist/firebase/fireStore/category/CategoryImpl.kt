package com.farkasatesz.myshoppinglist.firebase.fireStore.category

import com.farkasatesz.myshoppinglist.firebase.fireStore.FireStoreImpl
import com.farkasatesz.myshoppinglist.models.category.Category
import com.google.firebase.firestore.FirebaseFirestore

class CategoryImpl(db: FirebaseFirestore) : FireStoreImpl<Category>(db, "categories", Category::class.java)