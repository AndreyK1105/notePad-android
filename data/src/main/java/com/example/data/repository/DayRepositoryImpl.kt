package com.example.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.data.repository.mappers.DayRepositoryMapper
import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.storage.DayStorage
import com.example.domain.models.Day
import com.example.domain.repositoriy.DayRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DayRepositoryImpl (private val dayStorage: DayStorage, private val dayRepositoryMapper: DayRepositoryMapper): DayRepository {
    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getDay(date: Long): Flow<Day> {
       val dayRepositoryEntityFlow= dayStorage.getDay(date)
        var day :Day? = null

            dayRepositoryEntityFlow.collect {
                day=dayRepositoryMapper.toDay(it)

          }

        return if (day!=null){
            Log.v("dayReposImpl", "day.date=${day!!.date}")
            flow { emit(day!!) }
        }else flow<Day> {  }

      //  }else return flow {  }

    }

    override suspend fun addDay(day: Day): Boolean {
       return dayStorage.addDay( dayRepositoryMapper.toDayRepositoryEntity(day))
    }
}