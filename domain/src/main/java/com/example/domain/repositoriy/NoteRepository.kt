package com.example.domain.repositoriy


import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


interface NoteRepository {
    val notesFlow:Flow<List<Note>>
    suspend fun getNotes(): Flow<List<Note>>
    suspend fun getNote(id: Int): Note
    suspend fun addNote (note: Note):Boolean

}