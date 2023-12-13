package com.example.mynotepad.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Note
import com.example.domain.repositoriy.NoteRepository
import com.example.domain.usecase.AddNoteUseCase
import com.example.domain.usecase.DelNoteUseCase
import com.example.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val delNoteUseCase: DelNoteUseCase ,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase) : ViewModel( ) {
    //private val addNoteUseCase = domainModule
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"

    }
    var text: LiveData<String> = _text
    val notes: LiveData<List<Note>> =getNotesUseCase.notesFlow .asLiveData()
//    val notesFlow: Flow<List<Note>> =getNotesUseCase.execute()


    // private val _notes=MutableLiveData<List<Note>>().apply { value=[] }
    // val notes: LiveData<List<Note>>=_notes

    fun addNote(newText: String) {

viewModelScope.launch {
    addNoteUseCase.execute(Note(0,newText))
}






        _text.postValue(newText)
        Log.v("a", "set Text")
        Log.v("a", text.value.toString())
    }
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
    }

    override fun onCleared() {
        super.onCleared()
        Log.v("a", "ViewModelDashboard onClear")
    }

}