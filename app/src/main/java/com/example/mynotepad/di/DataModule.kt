package com.example.mynotepad.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.repository.DayRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import com.example.data.repository.TodoRepositoryImpl
import com.example.data.repository.mappers.DayRepositoryMapper
import com.example.data.repository.mappers.TodoRepositoryMapper
import com.example.data.storage.DayStorage
import com.example.data.storage.NoteStorage
import com.example.data.storage.TodoStorage
import com.example.data.storage.roomstorage.RoomDayStorage
import com.example.data.storage.roomstorage.RoomNoteStorage
import com.example.data.storage.roomstorage.RoomTodoStorage
import com.example.data.storage.roomstorage.mappers.DayRoomMapper
import com.example.data.storage.roomstorage.mappers.TodoRoomMapper
import com.example.data.storage.roomstorage.room.AppDatabase
import com.example.data.storage.roomstorage.room.DATA_BASE
import com.example.domain.repositoriy.DayRepository
import com.example.domain.repositoriy.NoteRepository
import com.example.domain.repositoriy.TodoRepository
import org.koin.dsl.module

val dataModule= module {


    single <TodoRoomMapper>{ TodoRoomMapper()  }
    single <DayRepositoryMapper>{ DayRepositoryMapper()  }
    single <DayRoomMapper>{DayRoomMapper()  }
    single<TodoRepositoryMapper>{TodoRepositoryMapper()}
    single <NoteStorage>{RoomNoteStorage(notesDao = get())  }
    single <DayStorage>{RoomDayStorage(daysDao = get(), todosDao = get(), dayRoomMapper = get(), todoRoomMapper = get())  }
    single <TodoStorage>{ RoomTodoStorage(todosDao = get(), todoRoomMapper = get(), daysDao = get()) }


    single <NoteRepository>{ NoteRepositoryImpl(noteStorage = get() )  }
    single <DayRepository>{ DayRepositoryImpl(dayStorage = get(), dayRepositoryMapper = get() )  }
    single<TodoRepository>{TodoRepositoryImpl(todoStorage = get(), todoRepositoryMapper = get())}


}

val dataBaseModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, DATA_BASE).build()}
    single { get <AppDatabase>().getNotesDao() }
    single { get <AppDatabase>().getDaysDao() }
    single { get <AppDatabase>().getTodosDao() }
}