package com.example.domain.usecase

import com.example.domain.models.Day
import com.example.domain.repositoriy.DayRepository

class GetDayUseCase (private val dayRepository: DayRepository){
    suspend fun execute(date: Long) : Day? {
        return dayRepository.getDay(date)
    }
}