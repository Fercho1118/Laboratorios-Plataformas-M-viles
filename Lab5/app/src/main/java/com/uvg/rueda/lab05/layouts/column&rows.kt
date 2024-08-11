package com.uvg.rueda.lab05.layouts

import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Update

@Composable
fun MyApp() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE3F2FD))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                androidx.compose.material3.Icon(imageVector = Icons.Default.Update, contentDescription = "Actualización disponible", tint = Color(0xFF3F51B5))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Actualización disponible", fontSize = 16.sp, fontWeight =  FontWeight.Medium, color = Color(0xFF3F51B5) )
            TextButton(onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                )
                context.startActivity(intent)
            }) {
                Text(
                    text = "Descargar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF3F51B5),
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column{
                Text(text = "Lunes", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Text(text = "18 de noviembre", fontSize = 20.sp, color = Color.Gray)
            }
            OutlinedButton(onClick = {/*Nada de momento*/}, shape = CutCornerShape(0)) {
                Text(text = "Terminar jornada")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Column(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Text(text = "T.G.I Friday's", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "C.C. Pradera Concepción", fontSize = 15.sp)
                    Text(text = "12:00PM - 11:55PM", fontSize = 12.sp)
                }
                IconButton(onClick = {
                    val uri = Uri.parse("geo:0,0?q=T.G.I+Friday's+C.C.+Pradera+Concepción,+1er+Nivel,+Local+151")
                    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                ){
                    Icon(Icons.Default.Directions, contentDescription = "Dirección")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ){
                Button(onClick = {
                    Toast.makeText(context, "Fernando Rueda", Toast.LENGTH_SHORT).show()
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)), shape = CutCornerShape(0), modifier = Modifier.weight(1f)
                ){
                    Text(text = "Iniciar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    Toast.makeText(context, "Comida Americana\nQQ", Toast.LENGTH_LONG).show()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),shape = CutCornerShape(0), modifier = Modifier.weight(1f)
                ){
                    Text(text = "Detalles")
                }
            }
        }
    }
}