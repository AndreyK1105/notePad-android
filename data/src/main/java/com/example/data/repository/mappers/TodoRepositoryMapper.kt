package com.example.data.repository.mappers

import com.example.data.repository.models.TodoRepositoryEntity
import com.example.domain.models.Todo

class TodoRepositoryMapper {
    fun toTodo( todoRepositoryEntity: TodoRepositoryEntity): Todo {
        return Todo(
            id=todoRepositoryEntity.id,
            dateLong=todoRepositoryEntity.dateLong,
            timeStart =todoRepositoryEntity.timeStart,
            timeEnd = todoRepositoryEntity.timeEnd,
            describe =todoRepositoryEntity.describe,
        )
    }

    fun toTodoRepositoryEntity(todo:Todo):TodoRepositoryEntity{
        return TodoRepositoryEntity(
            id=todo.id,
            dateLong=todo.dateLong,
            timeStart =todo.timeStart,
            timeEnd = todo.timeEnd,
            describe =todo.describe,

        )
    }
}