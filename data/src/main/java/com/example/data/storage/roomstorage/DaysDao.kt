package com.example.data.storage.roomstorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao

interface DaysDao {
    @Query("SELECT * FROM days_table WHERE date=:date ")

    suspend fun getByDate(date:Long): DayRoomEntity?

    @Query("SELECT*FROM days_table ")
    fun getAllDays(): Flow<List<DayRoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createDay(dayRoomEntity: DayRoomEntity)



//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun createNote(noteRoomEntity: NoteRoomEntity)
//
//    @Delete(entity= NoteRoomEntity::class )
//    suspend fun delNote(entity: NoteRoomEntity)

}