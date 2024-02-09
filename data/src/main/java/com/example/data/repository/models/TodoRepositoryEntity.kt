package com.example.data.repository.models

data class TodoRepositoryEntity(
    val id:Int,
    val dateLong :Long,
    val timeStart :Long,
    val timeEnd :Long,
    val describe : String
)
