package com.example.cronoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.cronoapp.navigation.NavManager.NavManager
import com.example.cronoapp.ui.theme.CronoAppTheme

import com.example.cronoapp.viewmodels.CronometroViewModel
import com.example.cronoapp.viewmodels.CronosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cronometroVM:CronometroViewModel by viewModels()
        val cronosVM:CronosViewModel by viewModels()

        setContent {
            CronoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(cronometroVM, cronosVM)
                }
            }
        }
    }
}

