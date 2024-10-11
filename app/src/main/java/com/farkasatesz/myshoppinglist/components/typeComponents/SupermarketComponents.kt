package com.farkasatesz.myshoppinglist.components.typeComponents

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseCreator
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseTopBar
import com.farkasatesz.myshoppinglist.components.baseComponents.MyButton
import com.farkasatesz.myshoppinglist.components.baseComponents.MyInput
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText

@Composable
fun SupermarketCreator(
    supermarketName: String,
    supermarketLocation: String,
    onSupermarketNameChange: (String) -> Unit,
    onSupermarketLocationChange: (String) -> Unit,
    checkExistence: Boolean,
    saveSupermarket: () -> Unit
) {
    val context = LocalContext.current
    BaseCreator {
        MyInput(
            value = supermarketName,
            valueChange = { onSupermarketNameChange(it) },
            label = "Supermarket",
            placeholder = "Enter name"
        )
        MyInput(
            value = supermarketLocation,
            valueChange = { onSupermarketLocationChange(it) } ,
            label = "Location",
            placeholder = "Enter location"
        )
        MyButton(
            text = {
                MyText(text = "Save")
            }
        ) {
            if(supermarketName.isNotEmpty() && supermarketLocation.isNotEmpty() && !checkExistence){
                saveSupermarket()
            }else if(supermarketName.isEmpty() || supermarketLocation.isEmpty()){
                Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
            }else if(checkExistence){
                Toast.makeText(context, "$supermarketName already exists", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SupermarketTopBar(
    supermarketName: String,
    supermarketLocation: String,
    onSupermarketNameChange: (String) -> Unit,
    onSupermarketLocationChange: (String) -> Unit,
    checkExistence: Boolean,
    saveSupermarket: () -> Unit
) {
    BaseTopBar(title = "Supermarkets"){
        SupermarketCreator(
            supermarketName,
            supermarketLocation,
            onSupermarketNameChange,
            onSupermarketLocationChange,
            checkExistence
        ) {
            saveSupermarket()
        }
    }

}