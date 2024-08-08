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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.fernando.rueda.lab04.R
import com.uvg.fernando.rueda.lab04.background.BorderColor
import org.w3c.dom.Text

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
            .padding(5.dp)
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleSection()
            MemberSection()
            ProfessorSection()
            StudentSection()
        }
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Universidad del Valle de Guatemala",
        fontSize = 35.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp,
        modifier = Modifier.padding(bottom = 20.dp)
    )
    Text(
        text = "Programación de plataformas móviles, Sección 30",
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        lineHeight = 30.sp,
        modifier = Modifier.padding(bottom = 20.dp)
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
            Text(text = "Fernando Rueda", modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = "Fernando Hernández", modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(text = "Juan Francisco Martínez", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun ProfessorSection(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "CATEDRÁTICO",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ){
            Text(text = "Juan Carlos Durini", modifier = Modifier.align(Alignment.Center))
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun StudentSection(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Fernando Rueda")
        Text(text = "23748")
    }
}