package com.farkasatesz.myshoppinglist.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.farkasatesz.myshoppinglist.components.baseComponents.BaseScaffold
import com.farkasatesz.myshoppinglist.components.typeComponents.ProductCard
import com.farkasatesz.myshoppinglist.components.typeComponents.ProductCreator
import com.farkasatesz.myshoppinglist.components.typeComponents.ProductTopBar
import com.farkasatesz.myshoppinglist.models.category.CategoryViewModel
import com.farkasatesz.myshoppinglist.models.product.Product
import com.farkasatesz.myshoppinglist.models.product.ProductViewModel
import com.farkasatesz.myshoppinglist.models.supermarket.SupermarketViewModel
import com.farkasatesz.myshoppinglist.models.unitType.UnitTypeViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun ProductScreen(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    unitTypeViewModel: UnitTypeViewModel,
    supermarketViewModel: SupermarketViewModel
) {

    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    val productName by productViewModel.itemName.collectAsState()
    val categories by categoryViewModel.items.collectAsState()
    val unitTypes by unitTypeViewModel.items.collectAsState()
    val supermarkets by supermarketViewModel.refs.collectAsState()
    val checkExistence by productViewModel.exists.collectAsState()
    var selectedCategoryId by remember {
        mutableStateOf("")
    }
    var selectedUnitTypeId by remember {
        mutableStateOf("")
    }




    BaseScaffold(
        viewModel = productViewModel,
        topBar = {
            ProductTopBar {
                ProductCreator(
                    selectedCategoryId = selectedCategoryId,
                    selectedUnitTypeId = selectedUnitTypeId,
                    categories = categories,
                    unitTypes = unitTypes,
                    selectCategory = {
                        selectedCategoryId = it
                        categoryViewModel.getCategoryReference(it)
                                     },
                    selectUnitType = {
                        selectedUnitTypeId = it
                        productViewModel.checkIfProductExists(productName, it)
                        unitTypeViewModel.getUnitTypeRef(it)
                                     },
                    productName = productName,
                    setProductName = {
                        productViewModel.setItemName(it)
                        productViewModel.checkIfProductExists(it, selectedUnitTypeId)
                                     },
                    checkExistence = checkExistence
                ) {
                    supermarkets.forEach{sm ->
                        val product = Product(
                            entityId = null,
                            entityName = productName,
                            quantity = 0.0,
                            price = 0.0
                        )
                        productViewModel.create(product)
                        selectedCategoryId = ""
                        selectedUnitTypeId = ""
                        productViewModel.setItemName("")
                    }

                }
            }
         },
        cardContent = {
            selectedProduct = it


        }
    ) {
        selectedProduct = it
    }
}