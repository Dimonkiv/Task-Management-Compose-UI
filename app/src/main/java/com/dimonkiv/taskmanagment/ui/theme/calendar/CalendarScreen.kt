package com.dimonkiv.taskmanagment.ui.theme.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dimonkiv.taskmanagment.R
import com.dimonkiv.taskmanagment.ui.theme.Primary
import com.dimonkiv.taskmanagment.ui.theme.Yellow
import com.dimonkiv.taskmanagment.model.CalendarItem
import com.dimonkiv.taskmanagment.model.Schedule

@Composable
fun CalendarScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
    ) {
        val viewModel: CalendarViewModel = viewModel()
        var selectedItem by remember {
            mutableIntStateOf(0)
        }
        var currentItem by remember {
            mutableStateOf(viewModel.selectedItem)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            TitleContent(title = "${currentItem.dayOfMonth} ${currentItem.month}")
            Spacer(modifier = Modifier.size(20.dp))
            CalendarContent(
                values = viewModel.getCalendarItems(),
                selected = selectedItem
            ) { pos, item ->
                selectedItem = pos
                currentItem = item
            }
            Spacer(modifier = Modifier.size(10.dp))

            BottomShadow()
            Spacer(modifier = Modifier.size(10.dp))

            VerticalSpacer()

            Spacer(modifier = Modifier.size(10.dp))

            Events(values = viewModel.getSchedules())
        }
    }
}

@Composable
fun BottomShadow(alpha: Float = 0.1f, height: Dp = 5.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = alpha),
                        Color.Transparent,
                    )
                )
            )
    )
}

@Composable
fun VerticalSpacer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .border(1.dp, Primary.copy(0.7f), CircleShape)
                .padding(6.dp)
                .clip(CircleShape)
                .background(Primary)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Primary.copy(0.7f))
                .align(Alignment.CenterVertically)
        )

    }
}

@Composable
fun TitleContent(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                color = Primary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "10 task for today",
                color = Primary,
                fontSize = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Calendar",
                tint = Color.White
            )
        }
    }
}

@Composable
fun CalendarContent(
    values: List<CalendarItem>,
    selected: Int,
    onItemClick: (Int, CalendarItem) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),

        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(values.size) { pos ->
            DayContent(item = values[pos], isSelected = pos == selected) {
                onItemClick(pos, values[pos])
            }
        }
    }
}

@Composable
fun DayContent(
    item: CalendarItem,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(55.dp)
            .height(85.dp)
            .clickable {
                onItemClick()
            }
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Yellow else Color.Transparent)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = item.dayOfMonth,
                color = if (isSelected) Primary else Primary.copy(0.7f),
                fontSize = if (isSelected) 20.sp else 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.dayOfWeek,
                color = if (isSelected) Primary else Primary.copy(0.7f),
                fontSize = if (isSelected) 18.sp else 16.sp,
            )
            if (isSelected) {
                Spacer(modifier = Modifier.size(8.dp))
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(Primary)
                )
            }
        }
    }

}

@Composable
fun Events(
    values: List<Schedule>
) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(values.size) {
            EventContent(item = values[it])
        }
    }

}

@Composable
fun EventContent(
    item: Schedule
) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(start = 12.dp)
    ) {
        Text(
            modifier = Modifier.weight(0.1f),
            text = item.hour,
            color = Primary.copy(0.7f),
            fontSize = 14.sp
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
        )

        item.event?.let { event ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(0.85f),
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(12.dp)
                            .background(event.colorRes)
                    )
                    Spacer(Modifier.size(12.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = event.colorRes.copy(0.2f))
                                .padding(5.dp),
                            text = event.type,
                            color = event.colorRes,
                            fontSize = 12.sp
                        )

                        Text(
                            text = event.title,
                            color = Primary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(14.dp),
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = "Time",
                                tint = Primary.copy(0.7f)
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = event.schedule,
                                color = Primary.copy(0.7f),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(18.dp),
                        tint = Primary.copy(0.7f)
                    )

                }
            }

        }

    }
}