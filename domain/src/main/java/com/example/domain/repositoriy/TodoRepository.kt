package com.example.domain.repositoriy

import com.example.domain.models.Todo

interface TodoRepository {
    suspend fun addTodo(todo: Todo):Boolean
    suspend fun getTodo(id:Int):Todo
}