package com.example.data.storage.roomstorage

import android.util.Log
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
//            Log.v("RoomTodoStorage", "todoReposEnt=$todoRepositoryEntity")
//            Log.v("RoomTodoStorage", "day.Id=${day.id}")


            todosDao.createTodo(todoRoomEntity)
            daysDao.updateDay(day)
            //
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
            val todoRoomEntity=todoRoomMapper.toTodoRoomEntity(todoRepositoryEntity, id.toInt())
            todosDao.createTodo(todoRoomEntity)
            return true
        }

    }

    override suspend fun delTodo(id: Int): Boolean {

       val todo=todosDao.getTodo(id)
        val day=daysDao.getByDate(todo.dateLong)
        todosDao.delTodo(todo)

        if (day != null) {
            daysDao.updateDay(day)
        }
        return true
    }
}