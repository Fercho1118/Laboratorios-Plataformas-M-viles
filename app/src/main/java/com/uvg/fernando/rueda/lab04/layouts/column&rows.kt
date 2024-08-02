package com.uvg.fernando.rueda.lab04.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.uvg.fernando.rueda.lab04.background.BorderColor

@Composable
fun MainContent(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ContentCard()
    }
}

@Composable
fun ContentCard() {
    val borderWidth = 10.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .drawBehind {
                drawRect(
                    color = BorderColor,
                    size = size,
                    style = Stroke(width = borderWidth.toPx())
                )
            }
    )
}