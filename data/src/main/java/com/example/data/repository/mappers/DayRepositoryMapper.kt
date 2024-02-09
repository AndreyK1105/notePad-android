package com.example.data.repository.mappers

import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.repository.models.TodoRepositoryEntity
import com.example.domain.models.Day
import com.example.domain.models.Todo

class DayRepositoryMapper {
    fun toDay (dayRepositoryEntity: DayRepositoryEntity): Day {
        val todos = arrayListOf<Todo>()
            dayRepositoryEntity.todos.map {
            todos.add( Todo(
                id = it.id,
                dateLong = it.dateLong,
                timeStart = it.timeStart,
                timeEnd = it.timeEnd,
                describe = it.describe

            )
            )
                   }
        return Day(
            dayNum = dayRepositoryEntity.dayNum,
            date=dayRepositoryEntity.date,
            isWeekend =dayRepositoryEntity.isWeekend,
            isCurrentMonth = dayRepositoryEntity.isCurrentMonth,
            id=dayRepositoryEntity.id,
            describe = dayRepositoryEntity.subscribe,
            todos = todos
        )
    }

    fun toDayRepositoryEntity( day: Day): DayRepositoryEntity{
        val todos=day.todos.map {
            TodoRepositoryEntity(
                id = it.id,
                dateLong = it.dateLong,
                timeStart = it.timeStart,
                timeEnd = it.timeEnd,
                describe = it.describe

            )
        }
        return DayRepositoryEntity(
            dayNum = day.dayNum,
            date=day.date,
            isWeekend =day.isWeekend,
            isCurrentMonth = day.isCurrentMonth,
            id=day.id,
            subscribe = day.describe,
            todos = todos
        )

    }
}