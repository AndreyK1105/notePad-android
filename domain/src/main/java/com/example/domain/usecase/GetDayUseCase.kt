package com.example.domain.usecase

import com.example.domain.models.Day
import com.example.domain.repositoriy.DayRepository
import kotlinx.coroutines.flow.Flow

class GetDayUseCase (private val dayRepository: DayRepository){
    suspend fun execute(date: Long) : Flow<Day> {
        return dayRepository.getDay(date)
    }
}