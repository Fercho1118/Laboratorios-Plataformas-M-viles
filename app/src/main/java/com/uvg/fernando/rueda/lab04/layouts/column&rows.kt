package com.uvg.fernando.rueda.lab04.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.fernando.rueda.lab04.R
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
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_uvg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleSection()
            MemberSection()
        }
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Universidad del Valle de Guatemala",
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    Text(
        text = "Programación de plataformas móviles, Sección 30",
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun MemberSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "INTEGRANTES", fontWeight = FontWeight.Bold)
        Column {
            Text(text = "Fernando Rueda")
            Text(text = "Fernando Hernández")
            Text(text = "Juan Francisco Martínez")
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}