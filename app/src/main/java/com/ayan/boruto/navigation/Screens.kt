package com.ayan.boruto.navigation

import com.ayan.boruto.util.Constant.DETAILS_NAV_ARG

sealed class Screens(val route: String){
    object Splash: Screens("splash_screen")
    object Welcome: Screens("welcome_screen")
    object Home: Screens("home_screen")
    object Details: Screens("details_screen/{$DETAILS_NAV_ARG}"){
        fun passHeroId(heroId: Int): String{
            return "details_screen/$heroId"
        }
    }
    object Search: Screens("search_screen")
}
