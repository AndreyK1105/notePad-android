package com.example.data.storage.roomstorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.storage.roomstorage.notes.entities.DayRoomEntity
import com.example.data.storage.roomstorage.notes.entities.NoteRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao

interface DaysDao {
    @Query("SELECT * FROM days_table WHERE date=:date ")

    suspend fun getByDate(date:Long): DayRoomEntity?

    @Query("SELECT*FROM days_table ")
    fun getAllDays(): Flow<List<DayRoomEntity>>

//    @Query("SELECT*FROM days_table WHERE id=:idDay")
//
//    suspend fun getDay(idDay:Int):Flow<DayRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createDay(dayRoomEntity: DayRoomEntity):Long

    @Update
    suspend fun updateDay(dayRoomEntity: DayRoomEntity)



//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun createNote(noteRoomEntity: NoteRoomEntity)
//
//    @Delete(entity= NoteRoomEntity::class )
//    suspend fun delNote(entity: NoteRoomEntity)

}