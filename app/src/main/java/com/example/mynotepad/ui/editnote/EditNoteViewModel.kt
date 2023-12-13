package com.example.mynotepad.ui.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Note
import com.example.domain.usecase.AddNoteUseCase
import com.example.domain.usecase.DelNoteUseCase
import com.example.domain.usecase.GetNotesUseCase
import java.io.Closeable

class EditNoteViewModel(
                        private val delNoteUseCase: DelNoteUseCase,
                        private val addNoteUseCase: AddNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase
                        ) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "empty text note"
    }

    var textNote:LiveData<String> =_text

//fun getNoteFromId(id:Int): Note {
//return
//// getNotesUseCase.
//}
}