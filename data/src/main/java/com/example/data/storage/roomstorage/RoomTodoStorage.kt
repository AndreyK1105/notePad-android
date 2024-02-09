package com.example.data.storage.roomstorage

import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.TodoStorage
import com.example.data.storage.roomstorage.mappers.TodoRoomMapper
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity

class RoomTodoStorage (
    private  val todosDao: TodosDao,
    private  val daysDao: DaysDao,
    private val todoRoomMapper: TodoRoomMapper
    ) : TodoStorage {
    override suspend fun getTodo(id: Int): TodoRepositoryEntity {
        val todoRoomEntity= todosDao.getTodo(id)
        return  todoRoomMapper.roomToTodoRepositoryEntity(todoRoomEntity)
    }

    override suspend fun getTodos(ownerId: Int): List<TodoRepositoryEntity> {
       return todosDao.getTodosForOwner(ownerId).map { todoRoomMapper.roomToTodoRepositoryEntity(it) }
    }

    override suspend fun addTodo(todoRepositoryEntity: TodoRepositoryEntity): Boolean {
       val date= todoRepositoryEntity.dateLong
        var day =daysDao.getByDate(date)
        if (day!=null){
            val todoRoomEntity=todoRoomMapper.toTodoRoomEntity(todoRepositoryEntity, day.id)
             todosDao.createTodo(todoRoomEntity)
       return true
        }else{
            day= DayRoomEntity(
               id=0,
                dayNum = 0,
                date=date,
                isWeekend = false,
                isCurrentMonth = false,
                subscribe = ""
            )
            val id= daysDao.createDay(day)
            val todoRoomEntity=todoRoomMapper.toTodoRoomEntity(todoRepositoryEntity, day.id)
            todosDao.createTodo(todoRoomEntity)
            return true
        }

    }

    override fun delTodo(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}