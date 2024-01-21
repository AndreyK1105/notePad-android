package com.example.data.storage

import com.example.data.repository.models.NoteRepositoryEntity
import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteStorage {
    val allNotes : Flow<List<NoteRepositoryEntity>>
    suspend fun getNotes(): Flow<List<NoteRepositoryEntity>>
    suspend fun  getNote(id: Int): NoteRepositoryEntity
    suspend fun addNote(note: Note):Boolean

    suspend fun delNote(id: Int):Boolean
}