package com.example.data.storage.roomstorage.notes.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="note_table")
 data class NoteRoomEntity(
                      @PrimaryKey(autoGenerate = true) val id: Int,
                      @ColumnInfo(name = "note")val note:String,
                      @ColumnInfo(name = "dateLong")val noteLong:Int)
