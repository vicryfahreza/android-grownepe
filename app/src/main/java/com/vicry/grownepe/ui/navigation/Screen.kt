package com.vicry.grownepe.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Article: Screen("article")
    object Detection: Screen("detection")
    object LMS: Screen("lms")
}