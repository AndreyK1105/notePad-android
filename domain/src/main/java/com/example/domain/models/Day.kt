package com.example.domain.models

data class Day(
    val dayNum: Int,
    val date: Long,
    val isWeekend: Boolean,
    val isCurrentMonth: Boolean,
    val id: Int,
    val describe: String,
    val todos : ArrayList<Todo>


){

}
