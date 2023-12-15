package com.example.domain.models

import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class Note(
    val id:Int,
    val textNote: String,
    val dateLong :Long     //System.currentTimeMillis()

    ) {
//   var id: Int = 0
//    var text: String=""

}