package com.example.data.storage.roomstorage.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Delete
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.data.storage.roomstorage.DaysDao
import com.example.data.storage.roomstorage.NotesDao
import com.example.data.storage.roomstorage.TodosDao
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity
import com.example.data.storage.roomstorage.notes.entities.TodoRoomEntity

const val DATA_BASE="database"
@Database(
    version = 5,
    entities = [
NoteRoomEntity::class,
    DayRoomEntity::class,
    TodoRoomEntity::class
    ],
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5)
    ],
    exportSchema = true
)

abstract class AppDatabase: RoomDatabase() {
abstract fun getNotesDao():NotesDao
abstract fun getDaysDao():DaysDao
abstract fun getTodosDao(): TodosDao

//@RenameColumn ()
//abstract class RoomAutoMigration: AutoMigrationSpec{}

companion object{
    @Volatile
    private var INSTANCE: AppDatabase?=null

    fun getDatabase(context: Context):AppDatabase {
        return INSTANCE?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase ::class.java,
                DATA_BASE
            ).build()
            INSTANCE=instance
            instance
        }
    }
}

}

//@Database(
//    version = 3,
//    entities = [
//        NoteRoomEntity::class
//    ],
//    autoMigrations = [
//        AutoMigration(from = 2, to = 3)
//    ]
//)
//
//public abstract class AppDatabase: RoomDatabase() {
//    abstract fun getNotesDao():NotesDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE: AppDatabase?=null
//
////    fun getDatabase(context: Context):AppDatabase {
////        return INSTANCE?: synchronized(this){
////            val instance = Room.databaseBuilder(
////                context.applicationContext,
////                AppDatabase ::class.java,
////                DATA_BASE
////            ).build()
////            INSTANCE=instance
////            instance
////        }
////    }
//    }
//
//}

