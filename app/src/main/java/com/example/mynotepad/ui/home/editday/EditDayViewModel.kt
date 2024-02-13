package com.example.mynotepad.ui.home.editday

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Day
import com.example.domain.models.Todo
import com.example.domain.usecase.AddDayUseCase
import com.example.domain.usecase.DelTodoUseCase
import com.example.domain.usecase.GetDayUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EditDayViewModel(
    private val addDayUseCase: AddDayUseCase,
    private val getDayUseCase: GetDayUseCase,
    private val delTodoUseCase: DelTodoUseCase


): ViewModel() {

   // private var todos: List<Todo> = listOf()
    private  var dateDay:String=""
    private  var day: Day= Day(dayNum = 0, date = 0,isWeekend = false, isCurrentMonth = true, id = 0, describe = "", todos= arrayListOf() )

     var todos: MutableLiveData <ArrayList<Todo>> = MutableLiveData <ArrayList<Todo>>(arrayListOf())
    lateinit var textDate:String

 @OptIn(InternalCoroutinesApi::class)
 suspend fun getDay(date:Long) {

     day=Day(dayNum = 0, date = date,isWeekend = false, isCurrentMonth = true, id = 0, describe = "", todos= arrayListOf() )
     viewModelScope.launch {
         val dayRepos =getDayUseCase.execute(date)
         if (dayRepos!=null){

             day=dayRepos
             todos.postValue(day.todos)
             //todos=day.todos
             textDate=day.date.toString()
             Log.v("editDayVM", "todos.size=${todos}")
                      }


    }
 // return   getDayUseCase.execute(date)
    }

    suspend fun addTodo(todo: Todo, describe:String){
    //todos.add(todo)
         day.todos.add(todo)
        val dayTodo = Day(
            dayNum = 0,
            date = todo.dateLong,isWeekend = false, isCurrentMonth = true, id = 0, describe = describe, todos= day.todos )
         addDayUseCase.execute(dayTodo)
    }

    suspend fun delTodo(id:Int){
        delTodoUseCase.execute(id)
    }

init {

}

}