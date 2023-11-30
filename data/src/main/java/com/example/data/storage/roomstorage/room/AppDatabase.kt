package com.example.data.storage.roomstorage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.storage.roomstorage.NotesDao
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity

@Database(
    version = 1,
    entities = [
NoteRoomEntity::class
    ]
)

abstract class AppDatabase: RoomDatabase() {
abstract fun notesDao():NotesDao

}