package com.uvg.rueda.lab7.layouts

import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
                "General" to NotificationType.GENERAL,
                "Nueva publicación" to NotificationType.NEW_POST,
                "Mensajes" to NotificationType.NEW_MESSAGE,
                "Likes" to NotificationType.NEW_LIKE
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

@Composable
fun NotificationItem(notification: Notification) {
    val backgroundColor = when (notification.type) {
        NotificationType.GENERAL -> Color(0xFFFFF9C4)
        NotificationType.NEW_POST -> Color(0xFFE3F2FD)
        NotificationType.NEW_MESSAGE -> Color(0xFFE0F7FA)
        NotificationType.NEW_LIKE -> Color(0xFFF3E5F5)
    }
    val icon = when (notification.type) {
        NotificationType.GENERAL -> Icons.Default.Notifications
        NotificationType.NEW_POST -> Icons.Default.Info
        NotificationType.NEW_MESSAGE -> Icons.Default.Email
        NotificationType.NEW_LIKE -> Icons.Default.ThumbUp
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = notification.body,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = notification.sendAt.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun NotificationList(
    notifications: List<Notification>,
    selectedFilter: NotificationType
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(notifications.filter { it.type == selectedFilter }) { notification ->
            NotificationItem(notification)
        }
    }
}

@Composable
fun NotificationScreen(onBackClick: () -> Unit) {
    Column {
        NotificationAppBar(onBackClick = onBackClick)
        var selectedFilter by remember { mutableStateOf(NotificationType.GENERAL) }
        val notifications = generateFakeNotifications()

        NotificationFilter(
            selectedFilter = selectedFilter,
            onFilterSelected = { filter -> selectedFilter = filter }
        )
        NotificationList(
            notifications = notifications,
            selectedFilter = selectedFilter
        )
    }
}

