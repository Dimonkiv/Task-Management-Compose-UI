package com.dimonkiv.taskmanagment.ui.theme.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.taskmanagment.R
import com.dimonkiv.taskmanagment.ui.theme.Primary
import com.dimonkiv.taskmanagment.ui.theme.Yellow

@Composable
fun MessageScreen() {
    val messages = chatItems()

    LazyColumn(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(messages.size) {
            Message(message = messages[it])
        }
    }
}

@Composable
fun Message(message: Message) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(50.dp)) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = message.image),
                contentDescription = "Photo"
            )
            Box(modifier = Modifier.size(8.dp)
                .clip(CircleShape)
                .align(Alignment.BottomEnd)
                .background(message.activeColor)
                .padding(40.dp)
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                text = message.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
            Text(
                text = message.message,
                fontSize = 14.sp,
                color = Primary.copy(0.7f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = message.date,
                color = Primary.copy(0.7f),
                fontSize = 12.sp
            )

            if (message.count != 0) {
                Text(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Primary),
                    textAlign = TextAlign.Center,
                    text = message.count.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

        }
    }
}

private fun chatItems(): List<Message> {
    val items = mutableListOf<Message>()

    items.add(
        Message(
            image = R.drawable.photo_1,
            name = "Jane",
            message = "Hi.",
            date = "18.04",
            count = 0,
            activeColor = Yellow
        )
    )
    items.add(
        Message(
            image = R.drawable.photo_2,
            name = "Mike",
            message = "How are you?",
            date = "9.0",
            count = 5,
            activeColor = Primary.copy(0.7f)

        )
    )
    items.add(
        Message(
            image = R.drawable.photo_1,
            name = "Kate",
            message = "Hi.",
            date = "18.04",
            count = 0,
            activeColor = Color.Yellow

        )
    )
    items.add(
        Message(
            image = R.drawable.photo_2,
            name = "Jane",
            message = "Hi.",
            date = "18.04",
            count = 0,
            activeColor = Color.Yellow

        )
    )
    items.add(
        Message(
            image = R.drawable.photo_1,
            name = "Jane",
            message = "Hi.",
            date = "18.04",
            count = 3,
            activeColor = Primary.copy(0.7f)

        )
    )
    items.add(
        Message(
            image = R.drawable.photo_1,
            name = "Jane",
            message = "Hi.",
            date = "18.04",
            count = 0,
            activeColor = Primary.copy(0.7f)

        )
    )
    items.add(
        Message(
            image = R.drawable.photo_2,
            name = "Jane",
            message = "Hi.",
            date = "18.04",
            count = 0,
            activeColor = Primary.copy(0.7f)

        )
    )
    items.add(
        Message(
            image = R.drawable.photo_1,
            name = "Jane",
            message = "Hi.",
            date = "18.04",
            count = 0,
            activeColor = Primary.copy(0.7f)

        )
    )

    return items
}