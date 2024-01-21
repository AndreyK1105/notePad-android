package com.example.data.storage.roomstorage

import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.TodoStorage

class RoomTodoStorage ( private  val todosDao: TodosDao) : TodoStorage {
    override suspend fun getTodo(id: Int): TodoRepositoryEntity {
        val todoRoomEntity= todosDao.getTodo(id)
        return  TodoRepositoryEntity(
            dateLong = todoRoomEntity.dateLong,
            timeStart = todoRoomEntity.timeStart,
            timeEnd = todoRoomEntity.timeEnd,
            describe = todoRoomEntity.describe,
        )
    }

    override suspend fun getTodos(ownerId: Int): List<TodoRepositoryEntity> {
       return todosDao.getTodosForOwner(ownerId).map { todoRoomEntity ->
           TodoRepositoryEntity(
           dateLong = todoRoomEntity.dateLong,
           timeStart = todoRoomEntity.timeStart,
           timeEnd = todoRoomEntity.timeEnd,
           describe = todoRoomEntity.describe
       ) }
    }

    override fun addTodo(note: TodoRepositoryEntity): Boolean {
        TODO("Not yet implemented")
    }

    override fun delTodo(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}