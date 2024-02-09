package com.example.data.storage

import com.example.data.repository.models.DayRepositoryEntity
import kotlinx.coroutines.flow.Flow

interface DayStorage {
    val allDays : Flow<List<DayRepositoryEntity>>
    suspend fun getDay(date: Long): DayRepositoryEntity?
    suspend fun getDays(): Flow<ArrayList<DayRepositoryEntity>>

    suspend fun addDay(day: DayRepositoryEntity) : Boolean
}