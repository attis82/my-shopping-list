package com.farkasatesz.myshoppinglist.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseScaffold
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText
import com.farkasatesz.myshoppinglist.components.typeComponents.CategoryCreator
import com.farkasatesz.myshoppinglist.components.typeComponents.CategoryTopBar
import com.farkasatesz.myshoppinglist.models.category.Category
import com.farkasatesz.myshoppinglist.models.category.CategoryViewModel

@Composable
fun CategoryScreen(viewModel: CategoryViewModel)  {
    val categoryName by viewModel.itemName.collectAsState()
    var name by remember { mutableStateOf("") }
    val exists by viewModel.exists.collectAsState()

    BaseScaffold(
        viewModel = viewModel,
        topBar = {
            CategoryTopBar(
                categoryName = categoryName,
                onCategoryNameChange = {
                    viewModel.setItemName(it)
                    viewModel.checkIfCategoryExists(it)
                },
                checkExistence = exists
            ) {
                val category = Category(entityId = null, entityName = categoryName)
                viewModel.create(category)
                viewModel.setItemName("")
            }
        },
        cardContent = {
            MyText(text = it.entityName)
        }
    ) {
        CategoryCreator(
            categoryName = name,
            onCategoryNameChange = {
                name = it
                viewModel.checkIfCategoryExists(name)
            },
            checkExistence = exists
        ) {
            val category = Category(entityId = it.entityId, entityName = name)
            viewModel.update(category)
        }
    }

}