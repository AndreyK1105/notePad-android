package com.example.domain.usecase

import com.example.domain.models.Day
import com.example.domain.repositoriy.DayRepository

class AddDayUseCase (private val dayRepository: DayRepository){
   suspend fun execute(day: Day): Boolean {
        return dayRepository.addDay(day)
    }
}