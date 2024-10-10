package com.farkasatesz.myshoppinglist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.farkasatesz.myshoppinglist.components.baseComponents.DeletionDialog
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText
import com.farkasatesz.myshoppinglist.components.categoryComponents.CategoryCreator
import com.farkasatesz.myshoppinglist.components.categoryComponents.CategoryList
import com.farkasatesz.myshoppinglist.components.categoryComponents.CategoryTopBar
import com.farkasatesz.myshoppinglist.models.category.Category
import com.farkasatesz.myshoppinglist.models.category.CategoryViewModel
import com.farkasatesz.myshoppinglist.ui.theme.BgColor

@Composable
fun CategoryScreen(viewModel: CategoryViewModel)  {
    val categoryName = viewModel.itemName.collectAsState()
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var name by remember { mutableStateOf("") }
    val exists = viewModel.exists.collectAsState()
    val categories = viewModel.items.collectAsState()
    var showDeletionDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor),
        topBar = {
            CategoryTopBar(
                categoryName = categoryName.value,
                onCategoryNameChange = {
                    viewModel.setItemName(it)
                    viewModel.checkIfCategoryExists(categoryName.value)
                                       },
                saveCategory = {
                    val cat = Category(entityId = null, entityName = categoryName.value)
                    viewModel.create(cat)
                    viewModel.setItemName("")
                               },
                checkExistence = { exists.value },
            )
        }
    ) {
        CategoryList(
            modifier = Modifier.padding(it),
            items = categories.value,
            delete = {
                showDeletionDialog = true
            },
            selectCategory = {
                selectedCategory = categories.value.find { cat-> cat.entityId == it.entityId }
                name = selectedCategory?.entityName ?: ""
            }
        ) {
            CategoryCreator(
                categoryName = name,
                onCategoryNameChange = { text -> name = text },
                saveCategory = {
                    val cat = Category(entityId = selectedCategory?.entityId, entityName = name)
                    viewModel.update(cat)
                    selectedCategory = null
                    viewModel.setItemName("")
                    name = ""
                }
            ) {
                exists.value
            }
        }
    }

    if (showDeletionDialog) {
        DeletionDialog(
            dismiss = { showDeletionDialog = false },
            itemName = selectedCategory?.entityName ?: "",
            delete = {
                viewModel.delete(selectedCategory?.entityId!!)
                selectedCategory = null
            }
        )

    }
}