package com.example.data.storage.roomstorage

import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.DayStorage
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class RoomDayStorage (
    private val daysDao : DaysDao,
    private val todosDao : TodosDao
): DayStorage {

    override val allDays: Flow<List<DayRepositoryEntity>> =daysDao.getAllDays().flatMapLatest { value ->
        flow {
            val days= arrayListOf<DayRepositoryEntity>()
            for (day in value){
                val ownerId = day.id

                // need checking for null list respond if todos is empty
                val todos = todosDao.getTodosForOwner(ownerId).map { todoRoomEntity ->
                    TodoRepositoryEntity(
                        dateLong = todoRoomEntity.dateLong,
                        timeStart = todoRoomEntity.timeStart,
                        timeEnd = todoRoomEntity.timeEnd,
                        describe = todoRoomEntity.describe
                    ) }
              days.add(
                  DayRepositoryEntity(
                 dayNum= day.dayNum,
                  date=day.date.toLong(),
                  isWeekend = day.isWeekend!=0,
                  isCurrentMonth =day.isCurrentMonth!=0,
                  id = day.id,
                  subscribe = day.subscribe,
                  todos=todos
                  )
              )

            }
            emit(days)
        } }
    override suspend fun getDay(date: Int): DayRepositoryEntity? {
        val dayRoomEntity= daysDao.getByDate(date)

        if(dayRoomEntity!=null) {
            val todos = todosDao.getTodosForOwner(dayRoomEntity.id).map { todoRoomEntity ->
                TodoRepositoryEntity(
                    dateLong = todoRoomEntity.dateLong,
                    timeStart = todoRoomEntity.timeStart,
                    timeEnd = todoRoomEntity.timeEnd,
                    describe = todoRoomEntity.describe
                ) }

            return DayRepositoryEntity(
                dayNum = dayRoomEntity.dayNum,
                date = dayRoomEntity.date,
                isWeekend = dayRoomEntity.isWeekend!=0,
                isCurrentMonth = dayRoomEntity.isCurrentMonth!=0,
                id = dayRoomEntity.id,
                subscribe = dayRoomEntity.subscribe,
                todos = todos
            )
        } else return null

    }

    override suspend fun addDay(day: DayRepositoryEntity): Boolean {
         daysDao.createDay(DayRoomEntity(
            id=0,
            dayNum = day.dayNum,
            date = day.date,
            isWeekend = if (day.isWeekend) 1 else 0,
            isCurrentMonth =if (day.isCurrentMonth) 1 else 0,
            subscribe = day.subscribe
        ))
        return true
    }

}