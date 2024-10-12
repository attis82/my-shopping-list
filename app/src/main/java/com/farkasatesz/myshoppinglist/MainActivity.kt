package com.farkasatesz.myshoppinglist

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.farkasatesz.myshoppinglist.screens.ProductScreen
import com.farkasatesz.myshoppinglist.ui.theme.BgColor
import com.farkasatesz.myshoppinglist.ui.theme.MyShoppingListTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBars(window)
        enableEdgeToEdge()
        setContent {
            MyShoppingListTheme {
                MyApp {
                    ProductScreen(productViewModel = koinViewModel())
                }
            }
        }
    }
}

@Composable
fun MyApp(content : @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor),
    ) {
        content()
    }
}

@RequiresApi(Build.VERSION_CODES.R)
private fun hideBars(window: Window){
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.decorView.setOnApplyWindowInsetsListener { _, insets ->
        window.insetsController?.apply {
            hide(android.view.WindowInsets.Type.systemBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        insets
    }
}
