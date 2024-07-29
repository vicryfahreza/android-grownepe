package com.vicry.grownepe

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vicry.grownepe.ui.navigation.NavigationItem
import com.vicry.grownepe.ui.navigation.Screen
import com.vicry.grownepe.ui.screen.article.ArticleScreen
import com.vicry.grownepe.ui.screen.article.cultivar.articlecultivar.CultivarScreen
import com.vicry.grownepe.ui.screen.article.cultivar.detailcultivar.DetailCultivarScreen
import com.vicry.grownepe.ui.screen.article.naturalhybrid.articlehybrid.NaturalHybridScreen
import com.vicry.grownepe.ui.screen.article.naturalhybrid.detailhybrid.DetailNHScreen
import com.vicry.grownepe.ui.screen.article.spesies.articlespecies.SpeciesScreen
import com.vicry.grownepe.ui.screen.article.spesies.detailspecies.DetailSpeciesScreen
import com.vicry.grownepe.ui.screen.detection.DetectionScreen
import com.vicry.grownepe.ui.screen.home.HomeScreen
import com.vicry.grownepe.ui.screen.lms.LmsScreen
import com.vicry.grownepe.ui.screen.lms.detailLms.DetailLmsScreen
import com.vicry.grownepe.ui.theme.GrowNepeTheme

@Composable
fun JetGrowNepeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Detection.route){
                DetectionScreen()
            }
            composable(Screen.Article.route){
                ArticleScreen(
                    speciesImage = stringResource(R.string.article_species_banner),
                    naturalHybridImage = stringResource(R.string.article_natural_hybrid_banner),
                    cultivarImage =  stringResource(R.string.article_cultivar_banner),
                    speciesLabel = stringResource(R.string.article_species_label),
                    naturalLabel = stringResource(R.string.article_natural_hybrid_label),
                    cultivarLabel = stringResource(R.string.article_cultivar_label),
                    navigateToSpesies = { navController.navigate(Screen.ArticleSpesies.route) },
                    navigateToCultivar = { navController.navigate(Screen.ArticleCultivar.route) },
                    navigateToNaturalHybrid = { navController.navigate(Screen.ArticleNaturalHybrid.route) },
                )
            }
            composable(
                route = Screen.DetailSpecies.route,
                arguments = listOf(navArgument("nepenthesId") { type = NavType.LongType } )
            ) {
                val id = it.arguments?.getLong("nepenthesId") ?: -1L
                DetailSpeciesScreen(
                    nepenthesId = id,
                )
            }
            composable(
                route = Screen.DetailCultivar.route,
                arguments = listOf(navArgument("nepenthesId") { type = NavType.LongType } )
            ) {
                val id = it.arguments?.getLong("nepenthesId") ?: -1L
                DetailCultivarScreen(
                    nepenthesId = id,
                )
            }
            composable(
                route = Screen.DetailNH.route,
                arguments = listOf(navArgument("nepenthesId") { type = NavType.LongType } )
            ) {
                val id = it.arguments?.getLong("nepenthesId") ?: -1L
                DetailNHScreen(
                    nepenthesId = id,
                )
            }
            composable(
                route = Screen.DetailLMS.route,
                arguments = listOf(navArgument("moduleId") { type = NavType.LongType } )
            ) {
                val id = it.arguments?.getLong("moduleId") ?: -1L
                DetailLmsScreen(
                    lmsId = id,
                )
            }
            composable(Screen.ArticleSpesies.route) {
                SpeciesScreen(
                    navigateToDetail = { nepenthesId ->
                        navController.navigate(Screen.DetailSpecies.createRoute(nepenthesId))
                    }
                )
            }
            composable(Screen.ArticleCultivar.route) {
                CultivarScreen(
                    navigateToDetail = { nepenthesId ->
                        navController.navigate(Screen.DetailCultivar.createRoute(nepenthesId))
                    }
                )
            }
            composable(Screen.ArticleNaturalHybrid.route) {
                NaturalHybridScreen(
                    navigateToDetail = { nepenthesId ->
                        navController.navigate(Screen.DetailNH.createRoute(nepenthesId))
                    }
                )
            }
            composable(Screen.LMS.route){
                LmsScreen(
                    navigateToModule = { modulId ->
                        navController.navigate(Screen.DetailLMS.createRoute(modulId))
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_detection),
                icon = ImageVector.vectorResource(R.drawable.ic_detection),
                screen = Screen.Detection
            ),
            NavigationItem(
                title = stringResource(R.string.menu_article),
                icon = ImageVector.vectorResource(R.drawable.ic_article),
                screen = Screen.Article
            ),
            NavigationItem(
                title = stringResource(R.string.menu_lms),
                icon = ImageVector.vectorResource(R.drawable.ic_lms),
                screen = Screen.LMS
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetGrowNepeAppPreview(){
    GrowNepeTheme {
        JetGrowNepeApp()
    }
}