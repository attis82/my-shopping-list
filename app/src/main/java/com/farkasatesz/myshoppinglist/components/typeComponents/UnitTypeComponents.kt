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
fun UnitTypeCreator(
    unitTypeName: String,
    onUnitTypeNameChange: (String) -> Unit,
    checkExistence: Boolean,
    saveUnitType: () -> Unit
) {
    val context = LocalContext.current
    BaseCreator {
        MyInput(
            value = unitTypeName,
            valueChange = { onUnitTypeNameChange(it) },
            label = "Unit type name",
            placeholder = "Enter unit type name"
        )
        MyButton(
            text = {
                MyText(text = "Save")
            }
        ) {
            if(unitTypeName.isNotEmpty() && !checkExistence){
                saveUnitType()
            }else if(unitTypeName.isEmpty()){
                Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
            }else if(checkExistence){
                Toast.makeText(context, "$unitTypeName already exists", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

@Composable
fun UnitTypeTopBar(
    unitTypeName: String,
    onUnitTypeNameChange: (String) -> Unit,
    checkExistence: Boolean,
    saveUnitType: () -> Unit
) {
    BaseTopBar(title = "Unit types") {
        UnitTypeCreator(
            unitTypeName,
            onUnitTypeNameChange,
            checkExistence
        ) {
            saveUnitType()
        }
    }
}
