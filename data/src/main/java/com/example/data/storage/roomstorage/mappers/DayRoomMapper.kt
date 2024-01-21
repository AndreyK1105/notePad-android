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
            isWeekend = dayRoomEntity.isWeekend!=0,
            isCurrentMonth = dayRoomEntity.isCurrentMonth!=0,
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
            isWeekend = if (dayRepositoryEntity.isWeekend) 1 else 0,
            isCurrentMonth =if (dayRepositoryEntity.isCurrentMonth) 1 else 0,
            subscribe = dayRepositoryEntity.subscribe
        )
    }
}