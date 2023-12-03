package com.example.data.storage.roomstorage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.storage.roomstorage.NotesDao
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity
const val DATA_BASE="database"
@Database(
    version = 2,
    entities = [
NoteRoomEntity::class
    ]
)

public abstract class AppDatabase: RoomDatabase() {
abstract fun getNotesDao():NotesDao

companion object{
    @Volatile
    private var INSTANCE: AppDatabase?=null

//    fun getDatabase(context: Context):AppDatabase {
//        return INSTANCE?: synchronized(this){
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase ::class.java,
//                DATA_BASE
//            ).build()
//            INSTANCE=instance
//            instance
//        }
//    }
}

}