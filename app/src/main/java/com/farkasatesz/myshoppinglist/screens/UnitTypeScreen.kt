package com.farkasatesz.myshoppinglist.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseScaffold
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText
import com.farkasatesz.myshoppinglist.components.typeComponents.UnitTypeCreator
import com.farkasatesz.myshoppinglist.components.typeComponents.UnitTypeTopBar
import com.farkasatesz.myshoppinglist.models.unitType.UnitType
import com.farkasatesz.myshoppinglist.models.unitType.UnitTypeViewModel

@Composable
fun UnitTypeScreen(viewModel: UnitTypeViewModel) {
    val unitTypeName by viewModel.itemName.collectAsState()
    val exists by viewModel.exists.collectAsState()
    var name by remember { mutableStateOf("") }

    BaseScaffold(
        viewModel = viewModel,
        topBar = {
            UnitTypeTopBar(
                unitTypeName = unitTypeName,
                onUnitTypeNameChange = {
                    viewModel.setItemName(it)
                    viewModel.checkIfUnitTypeExists(it)
                },
                checkExistence = exists
            ) {
                val unitType = UnitType(entityId = null, entityName = unitTypeName)
                viewModel.create(unitType)
                viewModel.setItemName("")
            }
        },
        cardContent = {
            MyText(text = it.entityName)
        }
    ) {
        UnitTypeCreator(
            unitTypeName = name,
            onUnitTypeNameChange = {
                text -> name = text
                viewModel.checkIfUnitTypeExists(name)
                                   },
            checkExistence = exists
        ) {
            val unitType = UnitType(entityId = it.entityId, entityName = name)
            viewModel.update(unitType)
        }
    }
}