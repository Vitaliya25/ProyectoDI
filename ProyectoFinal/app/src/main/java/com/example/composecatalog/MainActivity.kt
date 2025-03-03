package com.example.composecatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composecatalog.ui.theme.ComposeCatalogTheme
import com.example.composecatalog.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var  isDarkTheme by remember { mutableStateOf(false) }
            ComposeCatalogTheme (darkTheme = isDarkTheme){
                NavigationWrapper {isDarkTheme = !isDarkTheme}
                    }
                    }
                }
            }




