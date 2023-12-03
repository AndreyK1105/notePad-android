package com.example.data.storage.roomstorage

import com.example.data.repository.NoteRepositoryEntity
import com.example.data.storage.NoteStorage
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity
import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList

class RoomNoteStorage(
    private  val notesDao: NotesDao,

):NoteStorage {
    override val allNotes: Flow<List<NoteRepositoryEntity>> = notesDao.getAllNotes().flatMapLatest { value -> flow {

        val notes = arrayListOf<NoteRepositoryEntity>()
        for (note in value) {
            notes.add(NoteRepositoryEntity(note.id, note.note))
            emit(notes.toList())
        }

         }
    }
    override suspend fun getNotes(): Flow<List<NoteRepositoryEntity>> {
    return  notesDao.getAllNotes().map {it.map  {entity-> NoteRepositoryEntity(id= entity.id , textNote = entity.note) } }//  NoteRepositoryEntity(id= it , textNote = it.note) }

    //
    }

    override suspend fun getNote(id: Int): NoteRepositoryEntity {
       val noteRoomEntity=notesDao.getById(id)
        if (noteRoomEntity != null) {
            return  NoteRepositoryEntity(id =noteRoomEntity.id, textNote =  noteRoomEntity.note )
        }
        return NoteRepositoryEntity(id=0, textNote = "")
    }

    override suspend fun addNote(note: Note): Boolean {
     notesDao.createNote(NoteRoomEntity(id=note.id, note=note.textNote))
    return true//   TODO("Not yet implemented")
    }

}