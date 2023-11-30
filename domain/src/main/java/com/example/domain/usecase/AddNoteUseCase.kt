package com.example.domain.usecase

import com.example.domain.models.Note
import com.example.domain.repositoriy.NoteRepository

class AddNoteUseCase (private val noteRepository: NoteRepository){
    suspend fun execute(note: Note): Boolean {
        return noteRepository.addNote(note)
    }
}