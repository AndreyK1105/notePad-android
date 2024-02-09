package com.example.domain.repositoriy

import com.example.domain.models.Day
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    suspend fun getDay(date: Long): Day?
    suspend fun getDays(): Flow<ArrayList<Day>>
    suspend fun addDay(day: Day) : Boolean
}