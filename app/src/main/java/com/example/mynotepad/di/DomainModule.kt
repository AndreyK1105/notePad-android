package com.example.mynotepad.di

import com.example.domain.usecase.AddDayUseCase
import com.example.domain.usecase.AddNoteUseCase
import com.example.domain.usecase.AddTodoUseCase
import com.example.domain.usecase.DelNoteUseCase
import com.example.domain.usecase.GetDayUseCase
import com.example.domain.usecase.GetDaysUseCase
import com.example.domain.usecase.GetNoteUseCase
import com.example.domain.usecase.GetNotesUseCase
import com.example.domain.usecase.GetTodoUseCase
import org.koin.dsl.module

val domainModule=module {
    factory <AddNoteUseCase> { AddNoteUseCase(noteRepository = get())  }
    factory <GetNotesUseCase>{GetNotesUseCase( noteRepositoryLocal =  get ())  }
    factory <DelNoteUseCase> {DelNoteUseCase ( noteRepository= get())  }
    factory <GetNoteUseCase> {GetNoteUseCase ( noteRepository= get())  }
    factory <AddDayUseCase> {AddDayUseCase ( dayRepository = get())  }
    factory <GetDayUseCase> {GetDayUseCase ( dayRepository = get())  }
    factory <GetDaysUseCase> {GetDaysUseCase ( dayRepository = get())  }
    factory <AddTodoUseCase> {AddTodoUseCase( todorepository = get())  }
    factory <GetTodoUseCase> {GetTodoUseCase( todoRepository = get())  }

}