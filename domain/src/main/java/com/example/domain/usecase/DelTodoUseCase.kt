package com.example.domain.usecase

import com.example.domain.repositoriy.TodoRepository

class DelTodoUseCase (private val todoRepository: TodoRepository) {
    suspend fun execute(idTodo: Int){
        todoRepository.delTodo(idTodo)
    }
}