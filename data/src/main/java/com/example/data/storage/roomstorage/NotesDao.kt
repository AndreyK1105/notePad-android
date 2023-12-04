package com.example.data.storage.roomstorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity
import kotlinx.coroutines.flow.Flow


@Dao

interface NotesDao {
@Query("SELECT * FROM note_table WHERE id=:id ")

suspend fun getById(id:Int): NoteRoomEntity?

 @Query("SELECT*FROM note_table ")
  fun getAllNotes(): Flow<List<NoteRoomEntity>>


@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun createNote(noteRoomEntity: NoteRoomEntity)

@Delete(entity= NoteRoomEntity::class )
suspend fun delNote(entity: NoteRoomEntity)
}