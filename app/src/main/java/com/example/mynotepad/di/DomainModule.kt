package com.example.mynotepad.di

import com.example.domain.usecase.AddNoteUseCase
import com.example.domain.usecase.GetNotesUseCase
import org.koin.dsl.module

val domainModule=module {
    factory <AddNoteUseCase> { AddNoteUseCase(noteRepository = get())  }
    factory <GetNotesUseCase>{GetNotesUseCase( noteRepositoryLocal =  get ())  }

}