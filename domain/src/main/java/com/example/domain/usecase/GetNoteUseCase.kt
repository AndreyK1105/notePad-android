package com.example.domain.usecase

import com.example.domain.models.Note
import com.example.domain.repositoriy.NoteRepository

class GetNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(idNote:Int):Note{
        val note: Note =noteRepository.getNote(idNote)
        return note
    }
}