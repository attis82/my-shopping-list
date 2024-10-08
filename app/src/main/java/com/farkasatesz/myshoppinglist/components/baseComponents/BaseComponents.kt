package com.farkasatesz.myshoppinglist.components.baseComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farkasatesz.myshoppinglist.models.BaseEntity
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
        colors = TextFieldDefaults.colors(
            focusedTextColor = BgColor,
            focusedContainerColor = TextColor,
            unfocusedContainerColor = CardColor,
            unfocusedTextColor = TextColor,
            cursorColor = BgColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun <T: BaseEntity> MyCard(item : T){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor,
            contentColor = TextColor
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            MyText(text = item.entityName)
        }
    }

}

@Composable
fun <T: BaseEntity> ListView(
    items: List<T>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        items(items = items){ item->
            MyCard(item)
        }
    }
}