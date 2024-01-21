package com.example.data.storage

import com.example.data.repository.models.TodoRepositoryEntity

interface TodoStorage {
    suspend fun  getTodo(id: Int): TodoRepositoryEntity
    suspend fun  getTodos(ownerId: Int): List<TodoRepositoryEntity>
     fun addTodo(note: TodoRepositoryEntity):Boolean

     fun delTodo(id: Int):Boolean

}