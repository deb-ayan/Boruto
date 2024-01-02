package com.ayan.boruto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ayan.boruto.presentation.screens.SplashScreen
import com.ayan.boruto.util.Constant.DETAILS_NAV_ARG


@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ){
        composable(route = Screens.Splash.route){
            SplashScreen()
        }
        composable(route = Screens.Welcome.route){

        }
        composable(route = Screens.Home.route){

        }
        composable(
            route = Screens.Details.route,
            arguments = listOf(navArgument(DETAILS_NAV_ARG){type = NavType.IntType})
        ){

        }
        composable(route = Screens.Search.route){

        }
    }
}