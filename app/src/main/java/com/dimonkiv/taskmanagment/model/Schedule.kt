package com.dimonkiv.taskmanagment.model

data class Schedule(
    val hour: String,
    var event: EventItem? = null
)
