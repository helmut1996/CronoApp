package com.example.cronoapp.navigation.NavManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cronoapp.viewmodels.CronometroViewModel
import com.example.cronoapp.views.AddView
import com.example.cronoapp.views.EditView
import com.example.cronoapp.views.HomeView

@Composable
fun NavManager(cronometroVM: CronometroViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController)
        }
        composable("AddView"){
            AddView(navController,cronometroVM)
        }
        composable("EditView"){
            EditView(navController)
        }
    }
}
