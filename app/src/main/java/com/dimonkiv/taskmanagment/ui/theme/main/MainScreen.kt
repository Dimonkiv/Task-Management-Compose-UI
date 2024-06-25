package com.dimonkiv.taskmanagment.ui.theme.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.taskmanagment.R
import com.dimonkiv.taskmanagment.ui.theme.BluePurple
import com.dimonkiv.taskmanagment.ui.theme.GreenishYellow
import com.dimonkiv.taskmanagment.ui.theme.LightBlue
import com.dimonkiv.taskmanagment.ui.theme.Primary
import com.dimonkiv.taskmanagment.ui.theme.Secondary
import com.dimonkiv.taskmanagment.ui.theme.Yellow
import com.dimonkiv.taskmanagment.ui.theme.model.TaskItem
import com.dimonkiv.taskmanagment.ui.theme.model.TaskType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 50.dp),
    ) {
        Column {
            SearchContent()
            Spacer(modifier = Modifier.size(20.dp))
            TitleContent()
            Spacer(modifier = Modifier.size(20.dp))
            TaskTypes()
            Spacer(modifier = Modifier.size(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "All task",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.size(10.dp))
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "All task",
                    tint = Primary
                )
            }
            TaskContent()
        }
    }
}

@Composable
fun SearchContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
                .weight(0.85f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                    Text(text = "Search")
                }
            }
        )
        Spacer(modifier = Modifier.size(20.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(Primary)
                .weight(0.1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.List,
                contentDescription = "Settings",
                tint = Color.White
            )
        }
    }
}

@Composable
fun TitleContent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Today",
            color = Primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "date",
                tint = Primary.copy(0.7f)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "June 20, 2024",
                color = Primary.copy(0.7f)
            )
        }
    }
}

@Composable
fun TaskTypes() {
    val values = listOf(
        TaskType("Ongoing", "10 Tasks", Primary, Color.White),
        TaskType("Pending", "26 Tasks", LightBlue, Primary),
        TaskType("Completed", "10 Tasks", GreenishYellow, Primary),
        TaskType("Cancel", "20 Tasks", BluePurple, Primary)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(values.size) {
            TypeContent(type = values[it])
        }
    }
}

@Composable
fun TypeContent(type: TaskType) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(type.color),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Text(
                text = type.title,
                color = type.textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = type.count,
                color = type.textColor,
                fontSize = 18.sp,
            )
        }

        Icon(
            modifier = Modifier
                .padding(12.dp)
                .size(32.dp)
                .align(Alignment.BottomEnd),
            imageVector = Icons.Default.ArrowForward,
            contentDescription = type.title,
            tint = type.textColor,
        )
    }
}

@Composable
fun TaskContent() {
    val items = listOf(
        TaskItem("Mobile application design", Primary),
        TaskItem("Dashboard UI design", Yellow),
        TaskItem("Mobile application design", Primary),
        TaskItem("Dashboard UI design", Yellow),
        TaskItem("Mobile application design", Primary)

    )
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(
            start = 0.dp,
            end = 0.dp,
            bottom = 60.dp,
            top = 10.dp
        ),
        contentPadding = PaddingValues(
            start = 0.dp,
            end = 0.dp,
            top = 10.dp,
            bottom = 10.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items.size) {
            Task(title = items[it].title, color = items[it].color)
        }
    }
}

@Composable
fun Task(
    title: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .fillMaxWidth()
                        .weight(0.1f),
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    tint = color
                )
                Spacer(modifier = Modifier.size(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f)
                ) {
                    Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(15.dp))
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp),
                        progress = 0.5f,
                        color = color,
                        strokeCap = StrokeCap.Round
                    )
                }
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more",
                )
            }
        }
    }
}