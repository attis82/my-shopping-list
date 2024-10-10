package com.farkasatesz.myshoppinglist.components.categoryComponents

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseCard
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseCreator
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseList
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseTopBar
import com.farkasatesz.myshoppinglist.components.baseComponents.MyButton
import com.farkasatesz.myshoppinglist.components.baseComponents.MyInput
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText
import com.farkasatesz.myshoppinglist.models.category.Category

@Composable
fun CategoryCreator(
    categoryName: String,
    onCategoryNameChange: (String) -> Unit,
    saveCategory: () -> Unit,
    checkExistence: (String) -> Boolean,
) {
    val context = LocalContext.current
    BaseCreator {
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
    BaseList(
        modifier = modifier,
        items = items,
        delete = { delete() },
        selectItem = { selectCategory(it) },
        content = {
            CategoryCard(
                item = it,
                selectCategory = { cat -> selectCategory(cat) },
                delete = { delete() }
            ) {
                creator()
            }
        }
    ) {
        creator()
    }
}



