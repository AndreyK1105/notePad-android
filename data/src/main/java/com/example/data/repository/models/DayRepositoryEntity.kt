package com.example.data.repository.models

data class DayRepositoryEntity(
    val dayNum: Int,
    val date: Long,
    val isWeekend: Boolean,
    val isCurrentMonth: Boolean,
    val id: Int,
    val subscribe: String,
    val todos : List<TodoRepositoryEntity>

)
