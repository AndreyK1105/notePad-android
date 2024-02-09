package com.example.mynotepad.ui.home.edit_todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Todo
import com.example.domain.usecase.GetTodoUseCase

class EditTodoViewModel (
    private val getTodoUseCase: GetTodoUseCase
): ViewModel() {
    private var idTodo:Int=0
     private var _todo: MutableLiveData<Todo> =MutableLiveData<Todo>().apply{
         Todo( id=0, dateLong = 0, timeStart = 0, timeEnd =0, describe = "")
     }

    val todo=_todo

   suspend fun getTodo(id:Int){
       idTodo=id
       if (idTodo!=0){
            _todo.postValue(getTodoUseCase.execute(idTodo))
       }
   }

}