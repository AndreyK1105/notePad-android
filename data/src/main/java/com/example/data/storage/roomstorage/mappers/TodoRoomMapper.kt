package com.example.data.storage.roomstorage.mappers

import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.roomstorage.notes.entities.TodoRoomEntity

class TodoRoomMapper {

    fun roomToTodoRepositoryEntity(todoRoomEntity: TodoRoomEntity): TodoRepositoryEntity{
        return TodoRepositoryEntity(
            id=todoRoomEntity.id,
            dateLong = todoRoomEntity.dateLong,
            timeStart = todoRoomEntity.timeStart,
            timeEnd = todoRoomEntity.timeEnd,
            describe = todoRoomEntity.describe
        )
    }

    fun toTodoRoomEntity (todoRepositoryEntity: TodoRepositoryEntity, ownerId: Int) : TodoRoomEntity{
        return TodoRoomEntity(
            id=todoRepositoryEntity.id,
            ownerId=ownerId,
            dateLong = todoRepositoryEntity.dateLong,
            timeStart = todoRepositoryEntity.timeStart,
            timeEnd = todoRepositoryEntity.timeEnd,
            describe = todoRepositoryEntity.describe

        )
    }
}