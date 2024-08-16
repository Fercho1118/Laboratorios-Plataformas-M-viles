package com.uvg.rueda.laboratorio6.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppContadora() {
    var contador by remember { mutableStateOf(0)}
    var incrementroContador by remember { mutableStateOf(0)}
    var decrementoContador by remember { mutableStateOf(0)}
    var valorMaximo by remember { mutableStateOf(0)}
    var valorMinimo by remember { mutableStateOf(0)}
    var cambioContador by remember { mutableStateOf(0)}
    val historial = remember { mutableStateListOf<Pair<Int,Boolean>>()}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = "Fernando Rueda",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                    contador--
                    decrementoContador++
                    cambioContador++
                    valorMinimo = minOf(valorMinimo, contador)
                    historial.add(Pair(contador, false))
                },
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0071B8))
            ){
                Text(text = "-", color = Color.White, fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$contador",
                fontSize = 60.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    contador++
                    incrementroContador++
                    cambioContador++
                    valorMaximo = maxOf(valorMaximo, contador)
                    historial.add(Pair(contador, true))
                },
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0071B8)),
            ){
                Text(text = "+", color = Color.White, fontSize = 32.sp)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
            Text(text = buildAnnotatedString {
                append("Total incrementos: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(incrementroContador.toString())
                }
            }, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Text(text = buildAnnotatedString {
                append("Total decrementos: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(decrementoContador.toString())
                }
            }, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Text(text = buildAnnotatedString {
                append("Valor máximo: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(valorMaximo.toString())
                }
            }, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Text(text = buildAnnotatedString {
                append("Valor mínimo: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(valorMinimo.toString())
                }
            }, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Text(text = buildAnnotatedString {
                append("Total de cambios: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(cambioContador.toString())
                }
            }, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Text(text = "Historial: ", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 5
            ){
                historial.forEach{ entry ->
                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .background(
                                color = if (entry.second) Color(0xFF007F23) else Color(0xFFD52300),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = entry.first.toString(), color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}