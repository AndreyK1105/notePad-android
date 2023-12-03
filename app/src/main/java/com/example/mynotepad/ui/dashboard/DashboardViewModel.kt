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
import com.example.domain.usecase.GetNotesUseCase
import com.example.mynotepad.di.domainModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.dsl.module

class DashboardViewModel(private val repository: NoteRepository,private val addNoteUseCase: AddNoteUseCase, private val getNotesUseCase: GetNotesUseCase) : ViewModel( ) {
    //private val addNoteUseCase = domainModule
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"

    }
    var text: LiveData<String> = _text
    val notes: LiveData<List<Note>> =getNotesUseCase.notesFlow .asLiveData()
//    val notesFlow: Flow<List<Note>> =getNotesUseCase.execute()


    // private val _notes=MutableLiveData<List<Note>>().apply { value=[] }
    // val notes: LiveData<List<Note>>=_notes

   suspend fun setText(newText: String) {

viewModelScope.launch {
    addNoteUseCase.execute(Note(2,"q"))
}





        _text.postValue(newText)
        Log.v("a", "set Text")
        Log.v("a", text.value.toString())
    }

   init {

//       viewModelScope.launch{ _text = getNotesUseCase.execute().asLiveData() }
//       val notes: LiveData<List<Note>> =getNotesUseCase.execute().asLiveData()
        _text.postValue("text")
    }

}