package com.farkasatesz.myshoppinglist.components.baseComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farkasatesz.myshoppinglist.models.BaseEntity
import com.farkasatesz.myshoppinglist.models.BaseViewModel
import com.farkasatesz.myshoppinglist.ui.theme.BgColor
import com.farkasatesz.myshoppinglist.ui.theme.CardColor
import com.farkasatesz.myshoppinglist.ui.theme.TextColor

@Composable
fun MyText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 16,
    color: Color = TextColor,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            color = color
        )
    )
}

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    onClick: () -> Unit) {
    TextButton(
        onClick = {
            onClick()
        },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = TextColor,
            disabledContentColor = Color.Gray
        )
    ) {
        text()
    }
}

@Composable
fun MyInput(
    modifier: Modifier = Modifier,
    value: String,
    valueChange: (String) -> Unit,
    enabled: Boolean = true,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = { valueChange(it) },
        modifier = modifier,
        label = { MyText(text = label) },
        placeholder = { MyText(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            focusedTextColor = TextColor,
            focusedContainerColor = CardColor,
            unfocusedContainerColor = CardColor,
            unfocusedTextColor = BgColor,
            cursorColor = TextColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun DeletionDialog(
    dismiss: () -> Unit,
    itemName: String,
    delete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { dismiss() },
        confirmButton = {
            MyButton(text = {
                MyText(
                    text = "Delete",
                    color = Color.Red
                )
            }
            ) {
            delete()
            dismiss()
        } },
        dismissButton = {
            MyButton(text = {
                MyText(text = "Cancel")
            }) {
                dismiss()
            }
        },
        title = {
            MyText(text = "Deleting $itemName")
        },
        text = {
            MyText(text = "Are you sure you want to delete $itemName?")
        },
        tonalElevation = 20.dp
    )
}

@Composable
fun BaseTopBar(
    title: String,
    content: @Composable () -> Unit
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
                MyText(text = title, fontSize = 20, fontWeight = FontWeight.Thin)
                MyButton(
                    text = {
                        MyText(text = if(showCreator) "Hide creator" else "Show creator")
                    }
                ) {
                    showCreator = !showCreator
                }
            }
            AnimatedVisibility(showCreator){
                content()
            }
        }
    }
}

@Composable
fun <T: BaseEntity> BaseCard(
    item: T,
    selectItem: (T) -> Unit,
    delete: () -> Unit,
    content: @Composable () -> Unit,
    creator: @Composable () -> Unit
) {
    var showCreator by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        selectItem(item)
                        showCreator = !showCreator
                    },
                    onDoubleTap = {
                        selectItem(item)
                        delete()
                    }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = CardColor,
            contentColor = TextColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            content()
            AnimatedVisibility(showCreator){
                BaseCreator {
                    creator()
                }
            }
        }
    }

}

@Composable
fun BaseCreator(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = BgColor,
            contentColor = TextColor
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}


@Composable
fun <T: BaseEntity> BaseScaffold(
    viewModel: BaseViewModel<T>,
    topBar: @Composable () -> Unit,
    cardContent: @Composable (T) -> Unit,
    creator: @Composable (T) -> Unit
) {
    var selectedItem by remember { mutableStateOf<T?>(null) }
    var showDeletionDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    val items by viewModel.items.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor),
        topBar = {
            topBar()
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(items = items){ item ->
                BaseCard(
                    item = item,
                    selectItem = {
                        selectedItem = items.find { i -> i.entityId == item.entityId }
                        name = item.entityName
                    },
                    delete = { showDeletionDialog = true },
                    content = {
                        cardContent(item)
                    }
                ) {
                    creator(selectedItem!!)
                }
            }
        }
    }

    if (showDeletionDialog) {
        DeletionDialog(
            dismiss = { showDeletionDialog = false },
            itemName = selectedItem?.entityName ?: "",
            delete = {
                viewModel.delete(selectedItem?.entityId!!)
                selectedItem = null
            }
        )
    }
}

@Composable
fun <T:BaseEntity> DropDown(
    title: String,
    list: List<T>,
    selectItem:  (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<T?>(null) }

    MyButton(
        text = {
            MyText(text = selectedItem?.entityName ?: title)
        },

    ) {
        expanded = !expanded
    }
   DropdownMenu(
       expanded = expanded,
       onDismissRequest = { expanded = false },
       modifier = Modifier
           .fillMaxWidth()
           .padding(10.dp)
   ) {
       list.forEach {
           DropdownMenuItem(
               text = { MyText(text = it.entityName) },
               onClick = {
                   selectItem(it.entityId!!)
                   selectedItem = it
                   expanded = false
               }
           )
       }
   }
}

