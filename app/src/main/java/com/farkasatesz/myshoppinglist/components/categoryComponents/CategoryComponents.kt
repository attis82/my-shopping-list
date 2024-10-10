package com.farkasatesz.myshoppinglist.components.categoryComponents

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseCard
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseTopBar
import com.farkasatesz.myshoppinglist.components.baseComponents.MyButton
import com.farkasatesz.myshoppinglist.components.baseComponents.MyInput
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText
import com.farkasatesz.myshoppinglist.models.category.Category
import com.farkasatesz.myshoppinglist.ui.theme.BgColor
import com.farkasatesz.myshoppinglist.ui.theme.TextColor

@Composable
fun CategoryCreator(
    categoryName: String,
    onCategoryNameChange: (String) -> Unit,
    saveCategory: () -> Unit,
    checkExistence: (String) -> Boolean,
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(width = 1.dp, color = TextColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp)
                .background(BgColor),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyInput(
                value = categoryName,
                valueChange = { onCategoryNameChange(it) },
                label = "Category name",
                placeholder = "Enter category name"
            )
            MyButton(
                text = {
                    MyText(text = "Save")
                }
            ) {
                if(categoryName.isNotEmpty() && !checkExistence(categoryName)){
                    saveCategory()
                }else if(categoryName.isEmpty()){
                    Toast.makeText(context, "Please enter a category name", Toast.LENGTH_SHORT).show()
                }else if(checkExistence(categoryName)){
                    Toast.makeText(context, "Category already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun CategoryTopBar(
    categoryName: String,
    onCategoryNameChange: (String) -> Unit,
    saveCategory: () -> Unit,
    checkExistence: (String) -> Boolean
) {
    BaseTopBar(title = "Categories") {
        CategoryCreator(
            categoryName = categoryName,
            onCategoryNameChange = { onCategoryNameChange(it) },
            saveCategory = { saveCategory() }
        ) {
            checkExistence(it)
        }
    }
}

@Composable
fun CategoryCard(
    item: Category,
    selectCategory: (Category) -> Unit,
    delete: () -> Unit,
    creator: @Composable () -> Unit
) {
    BaseCard(
        item = item,
        selectItem ={selectCategory(item)} ,
        delete = { delete() },
        content = {
            MyText(text = item.entityName)
        }
    ) {
        creator()
    }
}

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    items: List<Category>,
    delete: () -> Unit,
    selectCategory: (Category) -> Unit,
    creator: @Composable () -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items=items){ category ->
            CategoryCard(
                item = category,
                selectCategory = {cat ->
                    selectCategory(cat)
                },
                delete = {delete()}
            ) {
                creator()
            }
        }
    }
}



