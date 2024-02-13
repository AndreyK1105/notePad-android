package com.example.data.storage.roomstorage

import android.util.Log
import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.DayStorage
import com.example.data.storage.roomstorage.mappers.DayRoomMapper
import com.example.data.storage.roomstorage.mappers.TodoRoomMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

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
       // Log.v("roomDayStorage", "getDay date=$date")
        val dayRoomEntity= daysDao.getByDate(date)

        return if(dayRoomEntity!=null) {
            val todosRoom = todosDao.getTodosForOwner(dayRoomEntity.id)
            val todos : List<TodoRepositoryEntity> = todosRoom.map { todoRoomMapper.roomToTodoRepositoryEntity(it) }
           // Log.v("roomDayStorage", " dayRoomEntity= $dayRoomEntity")


                val dayRepositoryEntity=dayRoomMapper.roomToDayRepositoryEntity(dayRoomEntity, todos)
              //  Log.v("roomDayStorage", "get  dayRepositoryEntity= $dayRepositoryEntity")
              dayRepositoryEntity

        } else null

    }

    override suspend fun getDays(): Flow<ArrayList<DayRepositoryEntity>> {

//        daysDao.getAllDays().onEach {Log.v("roomDayStorage", "get AllDays1")  }.collect(){
//
//        }
        return daysDao.getAllDays().flatMapLatest { value ->
            Log.v("roomDayStorage", "get AllDays2")
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

    }

    override suspend fun addDay(day: DayRepositoryEntity): Boolean {
         val ownerId=daysDao.createDay(dayRoomMapper.toDayRoomEntity(day))
        Log.v("roomDayStorage","ownerId day=${ownerId.toInt()}")
        for (todo in day.todos){
           todosDao.createTodo(todoRoomMapper.toTodoRoomEntity(todo, ownerId.toInt()))
        }

        return true
    }

}