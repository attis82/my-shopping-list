package com.farkasatesz.myshoppinglist.components.typeComponents

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseCreator
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseTopBar
import com.farkasatesz.myshoppinglist.components.baseComponents.DropDown
import com.farkasatesz.myshoppinglist.components.baseComponents.MyButton
import com.farkasatesz.myshoppinglist.components.baseComponents.MyInput
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText
import com.farkasatesz.myshoppinglist.models.category.Category
import com.farkasatesz.myshoppinglist.models.supermarket.Supermarket
import com.farkasatesz.myshoppinglist.models.unitType.UnitType

@Composable
fun ProductCreator(
    selectedCategoryId: String,
    selectedUnitTypeId: String,
    categories: List<Category>,
    unitTypes: List<UnitType>,
    selectCategory: (String) -> Unit,
    selectUnitType: (String) -> Unit,
    productName: String,
    setProductName: (String) -> Unit,
    checkExistence: Boolean,
    saveProduct: () -> Unit
) {
    val context = LocalContext.current

    BaseCreator {
        MyInput(
            value = productName,
            valueChange = { setProductName(it) },
            label = "Product name",
            placeholder = "Enter product name"
        )
        DropDown(list = categories) {
            selectCategory(it)
        }
        DropDown(list = unitTypes) {
            selectUnitType(it)
        }
        MyButton(
            text = {
                MyText(text = "Save product")
            }
        ) {
            if(checkExistence){
                Toast.makeText(context, "$productName already exists", Toast.LENGTH_SHORT).show()
            }else if(productName.isEmpty() || selectedCategoryId.isEmpty() || selectedUnitTypeId.isEmpty()){
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }else{
                saveProduct()
            }
        }
    }

}

@Composable
fun ProductCard(
    productName: String,
    categoryName: String,
    unitTypeName: String,
    quantity: String,
    onQuantityChange: (String) -> Unit,
    price: String,
    setPrice: (String) -> Unit,
    setQuantity: (String) -> Unit,
    onPriceChange: (String) -> Unit
) {
    var enabledQty by remember { mutableStateOf(false) }
    var enabledPrice by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
       Column(
           modifier = Modifier.fillMaxWidth(),
           verticalArrangement = Arrangement.SpaceEvenly,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
          Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
          ) {
              MyText(text = productName)
              MyText(text = categoryName)
          }
           Row (
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
           ){
               MyInput(
                   modifier = Modifier.pointerInput(Unit){
                       detectTapGestures(
                           onLongPress = {
                               enabledQty = !enabledQty
                           }
                       )
                   },
                   value = quantity,
                   valueChange = { onQuantityChange(it) },
                   label = "Quantity",
                   placeholder = "Enter quantity",
                   enabled = enabledQty
               )
               if(enabledQty){
                   MyButton(text = { MyText(text = "Set") }) {
                       setQuantity(quantity)
                   }
               }
               MyText(text = unitTypeName)
           }
           MyInput(
               modifier = Modifier.pointerInput(Unit){
                   detectTapGestures(
                       onLongPress = {
                           enabledPrice = !enabledPrice
                       }
                   )
               },
               value = price,
               valueChange = { onPriceChange(it) },
               label ="Price",
               placeholder = "Enter price",
               enabled = enabledPrice
           )
           if(enabledPrice){
               MyButton(text = { MyText(text = "Set") }) {
                   setPrice(price)
               }
           }
       } 
    }
}

@Composable
fun ProductTopBar(
    creator: @Composable () -> Unit
) {
    BaseTopBar(title = "Products") {
        creator()
    }

}