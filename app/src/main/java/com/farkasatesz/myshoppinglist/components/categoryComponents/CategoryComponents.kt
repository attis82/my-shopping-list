package com.farkasatesz.myshoppinglist.components.categoryComponents

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farkasatesz.myshoppinglist.components.baseComponents.ListView
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
    saveCategory: (Category) -> Unit,
    checkExistence: (String) -> Boolean,
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        border = BorderStroke(width = 1.dp, color = TextColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(BgColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
                    val category = Category(entityId = null, entityName = categoryName)
                    saveCategory(category)
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
fun CategoryList(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
   ListView(items = categories, modifier = modifier)
}

@Composable
fun CategoryTopBar(
    categoryName: String,
    onCategoryNameChange: (String) -> Unit,
    saveCategory: (Category) -> Unit,
    checkExistence: (String) -> Boolean
) {
    var showCreator by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(10.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
           ) {
               MyText(text = "Categories", fontSize = 20, fontWeight = FontWeight.Thin)
               MyButton(
                   text = {
                       MyText(text = if(showCreator) "Hide creator" else "Show creator")
                   }
               ) {
                  showCreator = !showCreator
               }
           }
            AnimatedVisibility(showCreator){
                CategoryCreator(
                    categoryName = categoryName,
                    onCategoryNameChange = { onCategoryNameChange(it) },
                    saveCategory = { saveCategory(it) }
                ) {
                    checkExistence(it)
                }
            }
        }
    }
}