package com.example.data.storage.sharedstorage

import com.example.data.repository.NoteRepositoryEntity
import com.example.data.storage.NoteStorage
import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SharedPrefNoteStorage(override val allNotes: Flow<List<NoteRepositoryEntity>>):NoteStorage {
    override suspend fun getNotes(): Flow<List<NoteRepositoryEntity>> {
        val listFlow= flow<List<NoteRepositoryEntity>> {
           emit (listOf<NoteRepositoryEntity>( NoteRepositoryEntity(id = 1, textNote = "wwwee"))) }
        return listFlow
    }

    override suspend fun getNote(id: Int):NoteRepositoryEntity {
       return  NoteRepositoryEntity(id, "default text")
    }

    override suspend fun addNote(note: Note): Boolean {
        return true
    }
}