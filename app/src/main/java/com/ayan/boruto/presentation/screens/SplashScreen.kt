package com.ayan.boruto.presentation.screens

import android.app.Activity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import com.ayan.boruto.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.ayan.boruto.navigation.Screens
import com.ayan.boruto.ui.theme.Purple40
import com.ayan.boruto.ui.theme.Purple80


@Composable
fun SplashScreen(navController: NavHostController) {
    val rotate = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = Unit) {
        rotate.animateTo(
            targetValue = 1080f,
            animationSpec = tween(durationMillis = 2500, delayMillis = 200)
        )
        if (rotate.targetValue == 1080f) navController.navigate(Screens.Welcome.route)
    }
    Splash(rotate.value)
}

@Composable
fun Splash(degress: Float) {
    val context = LocalContext.current
    val view = LocalView.current
    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = Purple40.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
    }

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Purple40, Purple80
                    )
                )
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degress),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}

@Preview
@Composable
fun SplashPreview() {
    Splash(0f)
}