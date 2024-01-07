package com.ayan.boruto.presentation.screens

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.ayan.boruto.domain.model.OnBoardingPage
import com.ayan.boruto.ui.theme.LightGray
import com.ayan.boruto.R
import com.ayan.boruto.ui.theme.DarkGray
import com.ayan.boruto.ui.theme.Purple40
import com.ayan.boruto.ui.theme.Purple80


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pageState = rememberPagerState(pageCount = { 3 })
    val context = LocalContext.current
    val view = LocalView.current
    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = Color.Transparent.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        ) {
        HorizontalPager(state = pageState, modifier = Modifier.weight(10f)) { position ->
            PagerScreen(pages[position])
        }
        HorizontalPagerIndicator(pageState = pageState, weight = 1f)
        AnimatedVisibility(visible = pageState.currentPage == 2) {
            FinishButton {}
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(
                R.string.on_boarding_image
            )
        )
        Text(
            text = onBoardingPage.title,
            color = DarkGray,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.sourcesans3_extrabold))
        )
        Text(
            text = onBoardingPage.description,
            color = DarkGray,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 10.dp)
                .alpha(0.5f),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.sourcesans3_regular))
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnScope.HorizontalPagerIndicator(pageState: PagerState, weight: Float) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .weight(weight),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageState.pageCount) { iteration ->
            val color =
                if (pageState.currentPage == iteration) Purple40 else Purple80
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }
    }
}

@Composable
fun FinishButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 20.dp).clickable { onClick() },
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Purple40)
                .padding(horizontal = 150.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Finish",
                fontFamily = FontFamily(Font(R.font.sourcesans3_regular)),
                color = Color.White,
                fontSize = 20.sp,
            )
        }
    }
}