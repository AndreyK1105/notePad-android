package com.example.data.storage.roomstorage

import android.util.Log
import com.example.data.network.models.Resource
import com.example.data.network.models.SingleUser
import com.example.data.network.retrofit.interfaces.RetrofitService
import com.example.data.repository.models.DayRepositoryEntity
import com.example.data.repository.models.TodoRepositoryEntity
import com.example.data.storage.DayStorage
import com.example.data.storage.roomstorage.mappers.DayRoomMapper
import com.example.data.storage.roomstorage.mappers.TodoRoomMapper
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity
import com.example.domain.models.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

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
      //  Log.v("roomDayStorage", "get AllDays1")
//        daysDao.getAllDays().onEach {Log.v("roomDayStorage", "get AllDays1")  }.collect(){
//
//        }
           // TODO:" get allDays  from net Storage and save in room"

        val retrofitService= RetrofitService.create()
        // .getCalendarList()


        retrofitService
            //.getCalendarList()
            .getListResource()
            .enqueue(object : Callback<Resource> {
                override fun onResponse(
                    call: retrofit2.Call<Resource>,
                    response: Response<Resource>
                ) {
                    CoroutineScope(Job()).launch {

                    delay(3000)
                        val todos= listOf<TodoRepositoryEntity>(TodoRepositoryEntity(id=0, dateLong = 1704031200000, timeEnd = 1, timeStart = 1, describe = "roror"))
                        val day=DayRepositoryEntity(dayNum = 1, date= 1704031200000L, isWeekend = false, isCurrentMonth = true, id =29  , subscribe = "new", todos=todos )
                       addDay(day)
                        //daysDao.updateDay(dayRoomMapper.toDayRoomEntity(day)  )
                        Log.v("roomDayStorage","addDay(day)=${day}" )
                    }

                    Log.v("roomDayStorage","homefragment response.body()=${response.body()}" )
                }

                override fun onFailure(call: retrofit2.Call<Resource>, t: Throwable) {
                    Log.v("roomDayStorage","homefragment onFailure t=${t}" )
                }

            })

        return daysDao.getAllDays().flatMapLatest { value ->
            Log.v("roomDayStorage", "get AllDays2")
            flow {
                val days= arrayListOf<DayRepositoryEntity>()
                for (day in value){
                    val ownerId = day.id

//                  todosDao.getTodosForOwnerFlow(ownerId)
//                       .flatMapLatest {
//                               listTodo->
//                           val todos= arrayListOf<TodoRepositoryEntity>()
//                           for (todo in listTodo){
//                               todos.add(todoRoomMapper.roomToTodoRepositoryEntity(todo))
//                           }
//                           days.add(dayRoomMapper.roomToDayRepositoryEntity(day,todos))
//                           flow {
//                               emit(todos)
//                           }
//                       }
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