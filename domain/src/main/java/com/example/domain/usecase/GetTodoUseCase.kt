package com.example.domain.usecase

import com.example.domain.models.Todo
import com.example.domain.repositoriy.TodoRepository

class GetTodoUseCase (
    private val todoRepository: TodoRepository
){
    suspend fun execute(id:Int):Todo{
        return todoRepository.getTodo(id)
    }
}