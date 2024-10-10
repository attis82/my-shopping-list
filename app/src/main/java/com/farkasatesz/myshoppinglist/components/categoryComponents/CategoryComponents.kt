package com.farkasatesz.myshoppinglist.components.categoryComponents

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseCreator
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseTopBar
import com.farkasatesz.myshoppinglist.components.baseComponents.MyButton
import com.farkasatesz.myshoppinglist.components.baseComponents.MyInput
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText

@Composable
fun CategoryCreator(
    categoryName: String,
    onCategoryNameChange: (String) -> Unit,
    checkExistence: Boolean,
    saveCategory: () -> Unit
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
            if(categoryName.isNotEmpty() && !checkExistence){
                saveCategory()
            }else if(categoryName.isEmpty()){
                Toast.makeText(context, "Please enter a category name", Toast.LENGTH_SHORT).show()
            }else if(checkExistence){
                Toast.makeText(context, "$categoryName already exists", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

@Composable
fun CategoryTopBar(
    categoryName: String,
    onCategoryNameChange: (String) -> Unit,
    checkExistence: Boolean,
    saveCategory: () -> Unit
) {
    BaseTopBar(title = "Categories") {
        CategoryCreator(
            categoryName = categoryName,
            onCategoryNameChange = { onCategoryNameChange(it) },
            checkExistence = checkExistence
        ) {
            saveCategory()
        }
    }
}
