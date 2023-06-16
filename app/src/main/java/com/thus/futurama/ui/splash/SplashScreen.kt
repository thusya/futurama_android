package com.thus.futurama.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.thus.futurama.ui.navigation.NavigationScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF00CCFF), Color(0xFF3366FF))
                    )
                ), contentAlignment = Alignment.Center
        ) {

            LaunchedEffect(key1 = true) {
                delay(2000)
                navController.navigate(NavigationScreen.HOME_SCREEN.name) {
                    popUpTo(NavigationScreen.SPLASH_SCREEN.name) {
                        inclusive = true
                    }
                }
            }
        }
    }
}