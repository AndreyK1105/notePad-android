package com.example.data.storage.roomstorage

import android.util.Log
import com.example.data.repository.models.NoteRepositoryEntity
import com.example.data.storage.NoteStorage
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity
import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RoomNoteStorage(
    private  val notesDao: NotesDao,

):NoteStorage {
    override val allNotes: Flow<List<NoteRepositoryEntity>> =
        notesDao.getAllNotes().flatMapLatest { value ->
            flow {

                val notes = arrayListOf<NoteRepositoryEntity>()
                for (note in value) {
                    notes.add(NoteRepositoryEntity(note.id, note.note, note.noteLong.toLong()))

                }
                emit(notes.toList())
            }
        }

    override suspend fun getNotes(): Flow<List<NoteRepositoryEntity>> {
        return notesDao.getAllNotes().map {
            it.map { entity ->
                NoteRepositoryEntity(
                    id = entity.id,
                    textNote = entity.note,
                    dateLong = entity.noteLong.toLong()
                )
            }
        }//  NoteRepositoryEntity(id= it , textNote = it.note) }

        //
    }

    override suspend fun getNote(id: Int): NoteRepositoryEntity {
        val noteRoomEntity = notesDao.getById(id)
        if (noteRoomEntity != null) {
            return NoteRepositoryEntity(id = noteRoomEntity.id, textNote = noteRoomEntity.note, dateLong = noteRoomEntity.noteLong.toLong())
        }
        return NoteRepositoryEntity(id = 0, textNote = "",0)
    }

    override suspend fun addNote(note: Note): Boolean {
        notesDao.createNote(NoteRoomEntity(id = note.id, note = note.textNote, noteLong = note.dateLong.toInt()))
        Log.v("a","roomNoteStorage note.dateLong= ${note.dateLong}" )
        Log.v("a","roomNoteStorage note.dateLong.toInt= ${note.dateLong.toInt()}" )
        return true//   TODO("Not yet implemented")
    }

    override suspend fun delNote(id: Int): Boolean {
        val note= NoteRoomEntity(id=id, note = "" ,0 )
        val resultDel = notesDao.delNote(note)
        return resultDel != null

    }
}