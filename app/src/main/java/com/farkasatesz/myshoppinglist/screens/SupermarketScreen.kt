package com.farkasatesz.myshoppinglist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseScaffold
import com.farkasatesz.myshoppinglist.components.baseComponents.MyText
import com.farkasatesz.myshoppinglist.components.typeComponents.SupermarketCreator
import com.farkasatesz.myshoppinglist.components.typeComponents.SupermarketTopBar
import com.farkasatesz.myshoppinglist.models.supermarket.Supermarket
import com.farkasatesz.myshoppinglist.models.supermarket.SupermarketViewModel


@Composable
fun SupermarketScreen(viewModel: SupermarketViewModel) {
    val supermarketName by viewModel.itemName.collectAsState()
    val supermarketLocation by viewModel.location.collectAsState()
    val editName by viewModel.editName.collectAsState()
    val editLocation by viewModel.location.collectAsState()
    val exists by viewModel.exists.collectAsState()

    var selectedSupermarket by remember { mutableStateOf<Supermarket?>(null) }

    if (selectedSupermarket != null) {
        viewModel.setEditName(selectedSupermarket!!.entityName)
        viewModel.setLocation(selectedSupermarket!!.location)
    }


    BaseScaffold(
        viewModel = viewModel,
        topBar = {
            SupermarketTopBar(
                supermarketName = supermarketName,
                supermarketLocation = supermarketLocation,
                onSupermarketNameChange = {
                    viewModel.setItemName(it)
                    viewModel.checkIfSupermarketExists(it, supermarketLocation)
                },
                onSupermarketLocationChange = {
                    viewModel.setLocation(it)
                    viewModel.checkIfSupermarketExists(supermarketName, it)
                },
                checkExistence = exists,
                saveSupermarket = {
                    val supermarket = Supermarket(
                        entityId = null,
                        entityName = supermarketName,
                        location = supermarketLocation
                    )
                    viewModel.create(supermarket)
                    viewModel.setItemName("")
                    viewModel.setLocation("")
                }
            )
        },
        cardContent = { supermarket ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyText(text = supermarket.entityName)
                MyText(text = supermarket.location)
            }
        }
    ) { supermarket ->
        selectedSupermarket = supermarket
        SupermarketCreator(
            supermarketName = editName,
            supermarketLocation = editLocation,
            onSupermarketNameChange = {
                viewModel.setEditName(it)
                viewModel.checkIfSupermarketExists(it, editLocation)
            },
            onSupermarketLocationChange = {
                viewModel.setLocation(it)
                viewModel.checkIfSupermarketExists(editName, it)
            },
            checkExistence = exists,
            saveSupermarket = {
                val updatedSupermarket = Supermarket(
                    entityId = supermarket.entityId,
                    entityName = editName,
                    location = editLocation
                )
                viewModel.update(updatedSupermarket)
                viewModel.setItemName("")
                viewModel.setEditName("")
                viewModel.setLocation("")
                selectedSupermarket = null
            }
        )
    }
}
