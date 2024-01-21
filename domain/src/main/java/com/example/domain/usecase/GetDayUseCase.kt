package com.example.domain.usecase

import com.example.domain.models.Day
import com.example.domain.repositoriy.DayRepository

class GetDayUseCase (private val dayRepository: DayRepository){
    suspend fun execute(date: Int) : Day? {
        return dayRepository.getDay(date)
    }
}