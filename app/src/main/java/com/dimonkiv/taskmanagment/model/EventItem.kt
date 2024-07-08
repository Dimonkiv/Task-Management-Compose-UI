package com.dimonkiv.taskmanagment.model

import androidx.compose.ui.graphics.Color

data class EventItem(
    val colorRes: Color,
    val type: String,
    val title: String,
    val schedule: String
)
