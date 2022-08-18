package com.dev.randomnamepicker.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.randomnamepicker.AppVM
import com.dev.randomnamepicker.routes.home.HomeRoute
import com.dev.randomnamepicker.routes.list.ListRoute

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box {
            NavHost(
                navController = navController,
                startDestination = Route.Home.path
            ) {
                composable(route = Route.Home.path) {
                    HomeRoute(navController)
                }
                composable(
                    route = Route.List.path + "/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) {
                    ListRoute(navController, it.arguments?.getString("id"))
                }
            }
        }
    }
}