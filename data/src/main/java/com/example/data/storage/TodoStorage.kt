package com.example.data.storage

import com.example.data.repository.models.TodoRepositoryEntity

interface TodoStorage {
    suspend fun  getTodo(id: Int): TodoRepositoryEntity
    suspend fun  getTodos(ownerId: Int): List<TodoRepositoryEntity>
     suspend fun addTodo(note: TodoRepositoryEntity):Boolean

     suspend fun delTodo(id: Int):Boolean

}