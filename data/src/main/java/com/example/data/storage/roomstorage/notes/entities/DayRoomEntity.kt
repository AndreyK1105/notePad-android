package com.example.data.storage.roomstorage.notes.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days_table")
data class DayRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "day_nym") val dayNum: Int,
    val date: Long,
    @ColumnInfo(name = "is_weekend")val isWeekend: Boolean,
    @ColumnInfo(name = "is_current_month")val isCurrentMonth: Boolean,
       val subscribe: String,
)
