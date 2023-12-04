package com.example.domain.usecase

import com.example.domain.repositoriy.NoteRepository

class DelNoteUseCase(private val noteRepository: NoteRepository) {
suspend fun execute( id: Int): Boolean{
    return noteRepository.delNote(id)
}
}