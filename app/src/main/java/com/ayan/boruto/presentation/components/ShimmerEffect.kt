package com.ayan.boruto.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayan.boruto.ui.theme.ShimmerLightGray
import com.ayan.boruto.ui.theme.ShimmerMediumLightGray

@Composable
fun ShimmerEffect() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(2){
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition(label = "")
    val alphaAnim = transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    ShimmerItem(alpha = alphaAnim.value)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .alpha(alpha),
        color = ShimmerLightGray,
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        Column(modifier = Modifier.padding(all = 16.dp), verticalArrangement = Arrangement.Bottom) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .height(30.dp)
                    .alpha(alpha),
                color = ShimmerMediumLightGray,
                shape = RoundedCornerShape(size = 10.dp)
            ) {}
            Spacer(modifier = Modifier.padding(all = 10.dp))
            repeat(3) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .alpha(alpha),
                    color = ShimmerMediumLightGray,
                    shape = RoundedCornerShape(size = 10.dp)
                ) {}
                Spacer(modifier = Modifier.padding(all = 5.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .size(20.dp)
                            .alpha(alpha),
                        color = ShimmerMediumLightGray,
                        shape = RoundedCornerShape(size = 10.dp)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = 10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun ShimmerItemPrev() {
    ShimmerEffect()
}