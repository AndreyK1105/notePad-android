package com.example.mynotepad.ui.dashboard

import com.example.domain.models.Day
import java.time.DayOfWeek

data class ItemRowCalendar(
    val days: ArrayList<Day>,
    val varTitle: Int,
    val dayOfWeek: Int,
    val monthOfYear: Int,
    val year: Int
)