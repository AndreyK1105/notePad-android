package com.example.mynotepad.ui.dashboard

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Note
import com.example.domain.usecase.AddNoteUseCase
import com.example.domain.usecase.DelNoteUseCase
import com.example.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val delNoteUseCase: DelNoteUseCase ,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase) : ViewModel() {
    //private val addNoteUseCase = domainModule
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"

    }
    var text: LiveData<String> = _text
    //val notes: LiveData<List<Note>> =getNotesUseCase.notesFlow .asLiveData()
    //val notes: LiveData<List<Note>> =getNotesUseCase.execute().asLiveData()
    val notes  = getNotesUseCase.execute().collect() //asLiveData()
    val notesLD: MutableLiveData<List<Note>> = MutableLiveData(listOf())
        //getNotesUseCase.execute().asLiveData()
    private val _uiState = MutableStateFlow(notes)

    //val uiState: StateFlow<LiveData<List<Note>>> = _uiState.asStateFlow()
    val uiState: StateFlow<List<Note>> = _uiState.asStateFlow()

//    val notesFlow: Flow<List<Note>> =getNotesUseCase.execute()


    // private val _notes=MutableLiveData<List<Note>>().apply { value=[] }
    // val notes: LiveData<List<Note>>=_notes

//    fun addNote(newText: String) {
//
//viewModelScope.launch {
//    addNoteUseCase.execute(Note(0,newText,0))
//}
//
//
//
//
//
//
//        _text.postValue(newText)
//        Log.v("a", "set Text")
//        Log.v("a", text.value.toString())
//    }
      fun delNote(id:Int){
        viewModelScope.launch {
            delNoteUseCase.execute(id)
        }

    }

   init {

//       viewModelScope.launch{ _text = getNotesUseCase.execute().asLiveData() }
//       val notes: LiveData<List<Note>> =getNotesUseCase.execute().asLiveData()


       _text.postValue("text")
       Log.v("a", "ViewModelDashboard init")

       //_uiState.emit()
    }

    override fun onCleared() {
        super.onCleared()
        Log.v("a", "ViewModelDashboard onClear")
    }

}