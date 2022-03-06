package com.jetpack.triangleskewspinindicator

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.runtime.*

@Composable
fun AnimateValues(
    values: List<Float>,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> {
    val groups by rememberUpdatedState(newValue = values.zipWithNext())
    val state = remember { mutableStateOf(values.first()) }

    LaunchedEffect(key1 = groups) {
        val (_, setValue) = state
        animate(
            initialValue = 0f,
            targetValue = groups.size.toFloat(),
            animationSpec = animationSpec,
        ) { frame, _ ->
            val integerPart = frame.toInt()
            val (initialValue, finalValue) = groups[frame.toInt()]
            val decimalPart = frame - integerPart
            setValue(
                initialValue + (finalValue - initialValue) * decimalPart
            )
        }
    }
    return state
}


















