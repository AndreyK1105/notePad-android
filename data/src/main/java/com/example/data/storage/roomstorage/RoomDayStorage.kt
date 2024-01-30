package com.example.data.storage.roomstorage

import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.DayStorage
import com.example.data.storage.roomstorage.mappers.DayRoomMapper
import com.example.data.storage.roomstorage.mappers.TodoRoomMapper
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class RoomDayStorage (
    private val daysDao : DaysDao,
    private val todosDao : TodosDao,
    private val dayRoomMapper: DayRoomMapper,
    private val todoRoomMapper: TodoRoomMapper,

): DayStorage {

    override val allDays: Flow<List<DayRepositoryEntity>> =daysDao.getAllDays().flatMapLatest { value ->
        flow {
            val days= arrayListOf<DayRepositoryEntity>()
            for (day in value){
                val ownerId = day.id

                // need checking for null list respond if todos is empty
                val todos = todosDao.getTodosForOwner(ownerId).map { todoRoomMapper.roomToTodoRepositoryEntity(it) }
              days.add(
                  dayRoomMapper.roomToDayRepositoryEntity(day,todos)
              )

            }
            emit(days)
        } }
    override suspend fun getDay(date: Long): DayRepositoryEntity? {
        val dayRoomEntity= daysDao.getByDate(date)

        if(dayRoomEntity!=null) {
            val todosRoom = todosDao.getTodosForOwner(dayRoomEntity.id)
            val todos : List<TodoRepositoryEntity> = if(todosRoom!=null) {
                todosRoom.map { todoRoomMapper.roomToTodoRepositoryEntity(it) }
            } else {
                listOf<TodoRepositoryEntity>()
            }
            return dayRoomMapper.roomToDayRepositoryEntity(dayRoomEntity, todos)
        } else return null

    }

    override suspend fun addDay(day: DayRepositoryEntity): Boolean {
         daysDao.createDay(dayRoomMapper.toDayRoomEntity(day))
        for (todo in day.todos){
            todosDao.createTodo(todoRoomMapper.toTodoRoomEntity(todo, day.id))
        }

        return true
    }

}