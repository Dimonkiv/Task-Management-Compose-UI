package com.dimonkiv.taskmanagment.ui.theme.chat

import androidx.compose.ui.graphics.Color

data class Message(
    val image: Int,
    val name: String,
    val message: String,
    val date: String,
    val count: Int,
    val activeColor: Color
)
