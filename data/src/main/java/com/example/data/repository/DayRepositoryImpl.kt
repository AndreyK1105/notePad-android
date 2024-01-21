package com.example.data.repository

import com.example.data.repository.mappers.DayRepositoryMapper
import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.DayStorage
import com.example.domain.models.Day
import com.example.domain.models.Todo
import com.example.domain.repositoriy.DayRepository

class DayRepositoryImpl (private val dayStorage: DayStorage, private val dayRepositoryMapper: DayRepositoryMapper): DayRepository {
    override suspend fun getDay(date: Int): Day? {
       val dayRepositoryEntity= dayStorage.getDay(date)
        if (dayRepositoryEntity!=null){
          return  dayRepositoryMapper.toDay(dayRepositoryEntity)
        }else return null

    }

    override suspend fun addDay(day: Day): Boolean {
       return dayStorage.addDay( dayRepositoryMapper.toDayRepositoryEntity(day))
    }
}