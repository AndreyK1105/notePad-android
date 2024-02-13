package com.example.data.storage.roomstorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.roomstorage.notes.entities.TodoRoomEntity

@Dao
interface TodosDao {
    @Query("SELECT * FROM todos_table WHERE owner_id IS :ownerId")
  suspend fun   getTodosForOwner(ownerId: Int) : List<TodoRoomEntity>

    @Query("SELECT * FROM todos_table WHERE id=:id ")
   suspend fun   getTodo(id: Int) : TodoRoomEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun createTodo(todoRoomEntity: TodoRoomEntity)

    @Delete(entity= TodoRoomEntity::class ) //"DELETE FROM parsed_database WHERE id = :id"  entity= TodoRoomEntity::class
     suspend fun delTodo(todoRoomEntity: TodoRoomEntity)
}

