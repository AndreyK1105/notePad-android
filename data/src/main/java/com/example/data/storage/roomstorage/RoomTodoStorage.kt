package com.example.data.storage.roomstorage

import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.TodoStorage
import com.example.data.storage.roomstorage.mappers.TodoRoomMapper

class RoomTodoStorage (
    private  val todosDao: TodosDao,
    private val todoRoomMapper: TodoRoomMapper
    ) : TodoStorage {
    override suspend fun getTodo(id: Int): TodoRepositoryEntity {
        val todoRoomEntity= todosDao.getTodo(id)
        return  todoRoomMapper.roomToTodoRepositoryEntity(todoRoomEntity)
    }

    override suspend fun getTodos(ownerId: Int): List<TodoRepositoryEntity> {
       return todosDao.getTodosForOwner(ownerId).map { todoRoomMapper.roomToTodoRepositoryEntity(it) }
    }

    override fun addTodo(note: TodoRepositoryEntity): Boolean {
        TODO("Not yet implemented")
    }

    override fun delTodo(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}