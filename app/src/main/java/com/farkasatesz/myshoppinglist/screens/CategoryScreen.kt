package com.farkasatesz.myshoppinglist.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.farkasatesz.myshoppinglist.components.categoryComponents.CategoryList
import com.farkasatesz.myshoppinglist.components.categoryComponents.CategoryTopBar
import com.farkasatesz.myshoppinglist.models.category.CategoryViewModel
import com.farkasatesz.myshoppinglist.ui.theme.BgColor

@Composable
fun CategoryScreen(viewModel: CategoryViewModel)  {
    val categoryName = viewModel.query.collectAsState()
    val exists = viewModel.exists.collectAsState()
    val categories = viewModel.items.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CategoryTopBar(
                categoryName = categoryName.value,
                onCategoryNameChange = {
                    viewModel.setQuery(it)
                    viewModel.checkIfCategoryExists(it)
                                       },
                saveCategory = {
                    category ->  viewModel.create(category)
                    viewModel.setQuery("")
                },
                checkExistence = {
                    exists.value
                }
            )
        },
        containerColor = BgColor
    ) {
       CategoryList(categories = categories.value, modifier = Modifier.padding(it))
    }
}