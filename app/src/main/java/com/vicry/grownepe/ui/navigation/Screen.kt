package com.vicry.grownepe.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")

    object HomeNepe : Screen("home/{homenepeId}") {
        fun createRoute(homenepeId: Long) = "home/$homenepeId"
    }

    object HomeSoil : Screen("home/soil/{homesoilId}") {
        fun createRoute(homesoilId: Long) = "home/soil/$homesoilId"
    }

    object Article: Screen("article")
    object Detection: Screen("detection")
    object LMS: Screen("lms")

    object DetailLMS : Screen("lms/{moduleId}") {
        fun createRoute(moduleId: Long) = "lms/$moduleId"
    }

    object ArticleSpesies : Screen("species")

    object InfoSpecies : Screen("infoSpecies")

    object InfoCultivar : Screen("infoCultivar")

    object InfoNaturalHybrid : Screen("infoNaturalHybrid")

    object DetailSpecies : Screen("species/{nepenthesId}") {
    fun createRoute(nepenthesId: Long) = "species/$nepenthesId"
    }

    object ArticleNaturalHybrid : Screen("naturalhybrid")

    object DetailNH : Screen("naturalhybrid/{nepenthesId}") {
        fun createRoute(nepenthesId: Long) = "naturalhybrid/$nepenthesId"
    }

    object ArticleCultivar : Screen("cultivar")

    object DetailCultivar : Screen("cultivar/{nepenthesId}") {
        fun createRoute(nepenthesId: Long) = "cultivar/$nepenthesId"
    }



}