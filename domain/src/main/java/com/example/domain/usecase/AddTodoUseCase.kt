package com.example.domain.usecase

import com.example.domain.models.Todo
import com.example.domain.repositoriy.TodoRepository

class AddTodoUseCase(private val todorepository: TodoRepository) {
suspend fun execute(todo: Todo):Boolean {
    return todorepository.addTodo(todo)
}
}