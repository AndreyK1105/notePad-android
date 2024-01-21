package com.example.data.storage.roomstorage.notes.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "todos_table",
    foreignKeys =[ ForeignKey(
    entity = DayRoomEntity::class, parentColumns=["id"], childColumns =  ["owner_id"],
    onDelete =ForeignKey.CASCADE,
    onUpdate =ForeignKey.CASCADE

) ])

data class TodoRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo (name = "owner_id") val ownerId : String,

    val dateLong :Long,
    val timeStart :Long,
    val timeEnd :Long,
    val describe : String
)
