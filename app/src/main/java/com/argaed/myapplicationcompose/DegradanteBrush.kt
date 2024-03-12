package com.argaed.myapplicationcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SweepGradient
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.argaed.myapplicationcompose.ui.theme.disabled01
import com.argaed.myapplicationcompose.ui.theme.interactive01
import com.argaed.myapplicationcompose.ui.theme.inverse01pressed



@Composable
 fun SweepGradientExample() {
    val colorStops = listOf(
        1f to interactive01,
        1f to disabled01,
        1f to inverse01pressed
    ).toTypedArray()
    val density = LocalDensity.current
    val centerX: Float
    val centerY: Float

    val brush = Brush.sweepGradient(
        colorStops = colorStops
    )
    Box(modifier = Modifier
        .size(width = 161.dp, height = 97.dp)
        .background(brush)
    )
}


@Composable
@Preview(showBackground = true)
fun DegradantePreview() {
    SweepGradientExample()
}