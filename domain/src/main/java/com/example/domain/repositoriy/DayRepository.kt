package com.example.domain.repositoriy

import com.example.domain.models.Day
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    suspend fun getDay(date: Long): Flow<Day>
    suspend fun addDay(day: Day) : Boolean
}