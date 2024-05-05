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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vicry.grownepe.ui.navigation.NavigationItem
import com.vicry.grownepe.ui.navigation.Screen
import com.vicry.grownepe.ui.screen.article.ArticleScreen
import com.vicry.grownepe.ui.screen.detection.DetectionScreen
import com.vicry.grownepe.ui.screen.home.HomeScreen
import com.vicry.grownepe.ui.screen.lms.LmsScreen
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
                ArticleScreen()
            }
            composable(Screen.LMS.route){
                LmsScreen()
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
fun jetGrowNepeAppPreview(){
    GrowNepeTheme {
        JetGrowNepeApp()
    }
}