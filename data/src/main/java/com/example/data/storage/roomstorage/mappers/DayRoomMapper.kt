package com.example.data.storage.roomstorage.mappers

import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.roomstorage.RoomTodoStorage
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity

class DayRoomMapper {
    fun roomToDayRepositoryEntity(dayRoomEntity: DayRoomEntity, todosRepository: List<TodoRepositoryEntity>) : DayRepositoryEntity{

        return DayRepositoryEntity(
            dayNum = dayRoomEntity.dayNum,
            date = dayRoomEntity.date,
            isWeekend = dayRoomEntity.isWeekend,
            isCurrentMonth = dayRoomEntity.isCurrentMonth,
            id = dayRoomEntity.id,
            subscribe = dayRoomEntity.subscribe,
            todos = todosRepository
        )

    }

    fun toDayRoomEntity (dayRepositoryEntity: DayRepositoryEntity): DayRoomEntity{
        return DayRoomEntity(
            id=0,
            dayNum = dayRepositoryEntity.dayNum,
            date = dayRepositoryEntity.date,
            isWeekend = dayRepositoryEntity.isWeekend,
            isCurrentMonth =dayRepositoryEntity.isCurrentMonth,
            subscribe = dayRepositoryEntity.subscribe
        )
    }
}