package com.farkasatesz.myshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.farkasatesz.myshoppinglist.models.supermarket.Supermarket
import com.farkasatesz.myshoppinglist.models.supermarket.SupermarketViewModel
import com.farkasatesz.myshoppinglist.ui.theme.MyShoppingListTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var name by remember { mutableStateOf("") }
            var location by remember { mutableStateOf("") }
            val vm = getViewModel<SupermarketViewModel>()
            MyShoppingListTheme {
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column {
                        TextField(value = name, onValueChange = { name = it } )
                        TextField(value = location, onValueChange = { location = it } )
                        val exists by vm.exists.collectAsState()
                        Button(
                            onClick = {
                                vm.checkIfSupermarketExists(name, location)
                            }
                        ) {
                            Text(text = exists.toString())
                        }

                    }
                }
            }
        }
    }
}
