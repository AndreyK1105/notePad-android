package com.example.mynotepad.di

import com.example.data.repository.NoteRepositoryImpl
import com.example.data.storage.NoteStorage
import com.example.data.storage.roomstorage.NotesDao_Impl
import com.example.data.storage.roomstorage.RoomNoteStorage
import com.example.data.storage.sharedstorage.SharedPrefNoteStorage
import com.example.domain.models.Note
import com.example.domain.repositoriy.NoteRepository
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module

val dataModule= module {

   // single <NotesDao_Impl>
    single <NoteStorage>{RoomNoteStorage(notesDao = get())  }

    single <NoteRepository>{ NoteRepositoryImpl(noteStorage = get(), notesFlow = get() )  }

}