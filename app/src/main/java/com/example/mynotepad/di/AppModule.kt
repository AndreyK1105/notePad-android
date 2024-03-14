package com.example.mynotepad.di

import com.example.domain.usecase.AddDayUseCase
import com.example.mynotepad.ui.dashboard.DashboardViewModel
import com.example.mynotepad.ui.editnote.EditNoteViewModel
import com.example.mynotepad.ui.home.HomeViewModel
import com.example.mynotepad.ui.home.edit_todo.EditTodoViewModel
import com.example.mynotepad.ui.home.editday.EditDayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule= module {
    viewModel<DashboardViewModel>{
        DashboardViewModel(addNoteUseCase = get(), getNotesUseCase = get(),delNoteUseCase=get() )
    }
}

val homeModule= module {
    viewModel<HomeViewModel >{
        HomeViewModel( addDayUseCase = get(), getDayUseCase = get(), getDaysUseCase = get(), application = get() )    }
}
val editNoteModule= module {
    viewModel{EditNoteViewModel( addNoteUseCase = get(), getNoteUseCase = get(),delNoteUseCase=get() )}
}

val editDayModule= module {
    viewModel{EditDayViewModel( addDayUseCase =get(), getDayUseCase = get(), delTodoUseCase = get()  )}
}

val editTodoModule= module {
    viewModel{ EditTodoViewModel(  getTodoUseCase = get(), addTodoUseCase = get()  ) }
}