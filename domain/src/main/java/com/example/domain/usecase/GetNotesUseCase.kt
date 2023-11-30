package com.example.domain.usecase

import com.example.domain.models.Note
import com.example.domain.repositoriy.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class GetNotesUseCase( private val noteRepositoryLocal:NoteRepository) {
    //val notesFlow=
    suspend fun execute():Flow<List<Note>> {
       val notes = noteRepositoryLocal.getNotes().flatMapLatest { value->flow{emit(value)  } }
      return notes
        //listOf(Note(1,""))
    }

}