package com.thus.futurama.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thus.futurama.ui.home.HomeScreen
import com.thus.futurama.ui.home.HomeViewModel
import com.thus.futurama.ui.navigation.NavigationScreen
import com.thus.futurama.ui.theme.FuturamaAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuturamaAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = NavigationScreen.HOME_SCREEN.name){
                    composable(NavigationScreen.HOME_SCREEN.name) {
                        HomeScreen(navController = navController, homeViewModel = homeViewModel)
                    }
                }
            }
        }
    }
}