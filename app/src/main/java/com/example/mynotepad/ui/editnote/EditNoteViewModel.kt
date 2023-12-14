package com.example.mynotepad.ui.editnote

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.models.Note
import com.example.domain.usecase.AddNoteUseCase
import com.example.domain.usecase.DelNoteUseCase
import com.example.domain.usecase.GetNoteUseCase

class EditNoteViewModel(
                        private val delNoteUseCase: DelNoteUseCase,
                        private val addNoteUseCase: AddNoteUseCase,
                        private val getNoteUseCase: GetNoteUseCase

                        ) : ViewModel() {
//    private val _text = MutableLiveData<String>().apply {
//        value = ""
//    }

    private var _text=""
    var editTextNote:String =_text

    var saveText:String=""
suspend  fun getNoteFromId(idNote:Int) {

    val note = getNoteUseCase.execute(idNote)
    _text=note.textNote

    saveText=note.textNote
    Log.v("a", "EditNote saveText 1 =$saveText")

}
    fun saveText(text:String){
      editTextNote= text
    }
   suspend fun saveNote(note: Note){
        addNoteUseCase.execute(note)
    }
    suspend fun delNote(idNote: Int){
        delNoteUseCase.execute(idNote)
    }

}