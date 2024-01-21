package com.example.domain.repositoriy

import com.example.domain.models.Day

interface DayRepository {
    suspend fun getDay(date: Int): Day?
    suspend fun addDay(day: Day) : Boolean
}