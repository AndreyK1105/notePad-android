package com.example.mynotepad.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.repository.NoteRepositoryImpl
import com.example.data.storage.NoteStorage
import com.example.data.storage.roomstorage.NotesDao_Impl
import com.example.data.storage.roomstorage.RoomNoteStorage
import com.example.data.storage.roomstorage.room.AppDatabase
import com.example.data.storage.roomstorage.room.DATA_BASE
import com.example.data.storage.sharedstorage.SharedPrefNoteStorage
import com.example.domain.models.Note
import com.example.domain.repositoriy.NoteRepository
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module

val dataModule= module {


    single <NoteStorage>{RoomNoteStorage(notesDao = get())  }

    single <NoteRepository>{ NoteRepositoryImpl(noteStorage = get() )  }

}

val dataBaseModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, DATA_BASE).build()}
    single { get <AppDatabase>().getNotesDao() }
}