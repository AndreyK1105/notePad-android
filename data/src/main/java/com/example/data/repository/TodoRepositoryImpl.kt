package com.example.data.repository

import com.example.data.repository.mappers.DayRepositoryMapper
import com.example.data.repository.mappers.TodoRepositoryMapper
import com.example.data.storage.TodoStorage
import com.example.domain.models.Todo
import com.example.domain.repositoriy.TodoRepository

class TodoRepositoryImpl(private val todoStorage:TodoStorage, private val todoRepositoryMapper: TodoRepositoryMapper) :TodoRepository {
    override suspend fun addTodo(todo: Todo): Boolean {
        return todoStorage.addTodo(todoRepositoryMapper.toTodoRepositoryEntity(todo))
    }

    override suspend fun getTodo(id: Int): Todo {
       return todoRepositoryMapper.toTodo(todoStorage.getTodo(id))
    }

    override suspend fun delTodo(id: Int): Boolean {
       return todoStorage.delTodo(id)
    }
}