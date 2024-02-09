package com.example.domain.usecase

import com.example.domain.models.Day
import com.example.domain.repositoriy.DayRepository
import kotlinx.coroutines.flow.Flow

class GetDaysUseCase (private val dayRepository: DayRepository){
    suspend fun execute() : Flow<ArrayList<Day>> {
        return dayRepository.getDays()
    }

}