package com.example.domain.models

data class Todo(
    val id:Int,
    var dateLong :Long,
    val timeStart :Long,
    val timeEnd :Long,
    var describe : String

)
