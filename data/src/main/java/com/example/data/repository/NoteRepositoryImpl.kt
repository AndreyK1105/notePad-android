package com.example.data.repository

import com.example.data.storage.NoteStorage
import com.example.domain.models.Note
import com.example.domain.repositoriy.NoteRepository
import kotlinx.coroutines.flow.Flow


import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl (private val noteStorage: NoteStorage,

): NoteRepository{

    override val notesFlow: Flow<List<Note>> =

        noteStorage.allNotes.flatMapLatest { value ->


            flow{
                val notes = arrayListOf<Note>()
                for (note in value) {
                    notes.add(Note(note.id, note.textNote))

                }
                emit(notes.toList())
            }
        }


    override suspend fun getNotes(): Flow<List<Note>> {

       val notesRepositoryEntity=noteStorage.getNotes().flatMapLatest {value->

           flow{
               val notes = arrayListOf<Note>()
               for (note in value) {
                   notes.add(Note(note.id, note.textNote))

               }
               emit(notes.toList())
           }

          }


        return notesRepositoryEntity
    }

    override suspend fun getNote(id: Int): Note {
       val noteRepositoryEntity=noteStorage.getNote(id)
        return Note(noteRepositoryEntity.id, noteRepositoryEntity.textNote)
    }

    override suspend fun addNote(note: Note): Boolean {
         return noteStorage.addNote(note)

    }

    override suspend fun delNote(id: Int): Boolean {
        return noteStorage.delNote(id)
    }

}