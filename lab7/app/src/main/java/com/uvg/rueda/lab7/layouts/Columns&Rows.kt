package com.uvg.rueda.lab7.layouts

import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date

enum class NotificationType {
    GENERAL,
    NEW_POST,
    NEW_MESSAGE,
    NEW_LIKE
}

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val sendAt: Date,
    val type: NotificationType
)

fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(java.time.ZoneId.systemDefault()).toInstant())
}

fun generateFakeNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()
    val titles = listOf(
        "Nueva versión disponible",
        "Nuevo post de Juan",
        "Mensaje de Maria",
        "Te ha gustado una publicación"
    )
    val bodies = listOf(
        "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!",
        "Te han etiquetado en un nuevo post. ¡Míralo ahora!",
        "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.",
        "A Juan le ha gustado tu publicación. ¡Revisa tu perfil!"
    )
    val types = NotificationType.entries.toTypedArray()

    val currentDate = LocalDate.now()
    for (i in 1..50) {
        val daysAgo = (0..10).random()
        val hoursAgo = (0..23).random()
        val minutesAgo = (0..59).random()
        val sendAt = LocalDateTime.of(currentDate.minusDays(daysAgo.toLong()), LocalTime.of(hoursAgo, minutesAgo)).toDate()
        notifications.add(
            Notification(
                id = i,
                title = titles.random(),
                body = bodies.random(),
                sendAt = sendAt,
                type = types.random()
            )
        )
    }
    return notifications
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationAppBar(onBackClick: () -> Unit){
    TopAppBar(
        title = { Text(text = "Notificaciones")},
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back" )
            }
        }
    )
}

@Composable
fun NotificationFilter(
    selectedFilter: NotificationType,
    onFilterSelected: (NotificationType) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Tipos de notificaciones",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val filters = listOf(
                "Informativas" to NotificationType.GENERAL,
                "Capacitaciones" to NotificationType.NEW_POST 
            )

            filters.forEach { (text, type) ->
                FilterChip(
                    selected = type == selectedFilter,
                    onClick = { onFilterSelected(type) },
                    label = { Text(text = text) },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

