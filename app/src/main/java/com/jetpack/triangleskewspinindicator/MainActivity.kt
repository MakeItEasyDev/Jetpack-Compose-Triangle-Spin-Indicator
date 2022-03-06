package com.jetpack.triangleskewspinindicator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jetpack.triangleskewspinindicator.ui.theme.TriangleSkewSpinIndicatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriangleSkewSpinIndicatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Triangle Spin Indicator",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            TriangleSpinIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TriangleSpinIndicator() {
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(
            durationMillis = 2500,
            easing = LinearEasing,
        )
    )
    val xRotation by AnimateValues(
        values = listOf(0f, 180f, 180f, 0f, 0f),
        animationSpec = animationSpec
    )
    val yRotation by AnimateValues(
        values = listOf(0f, 0f, 180f, 180f, 0f),
        animationSpec = animationSpec
    )
    Triangle(
        modifier = Modifier.graphicsLayer(
            rotationX = xRotation,
            rotationY = yRotation,
        )
    )
}

@Composable
fun Triangle(
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    backgroundColor: Color = Color.Red,
) {
    Box(
        modifier = modifier
            .size(size)
            .clipToBounds()
            .background(backgroundColor, TriangleShape)
    )
}

private val TriangleShape = GenericShape { size, _ ->
    moveTo(size.width / 2f, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
}


























