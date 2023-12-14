package com.example.mynotepad.di

import com.example.mynotepad.ui.dashboard.DashboardViewModel
import com.example.mynotepad.ui.editnote.EditNoteViewModel
import com.example.mynotepad.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule= module {
    viewModel<DashboardViewModel>{
        DashboardViewModel(addNoteUseCase = get(), getNotesUseCase = get(),delNoteUseCase=get() )
    }
}

val homeModule= module {
    viewModel<HomeViewModel >{
        HomeViewModel( )    }
}
val editNoteModule= module {
    viewModel{EditNoteViewModel( addNoteUseCase = get(), getNoteUseCase = get(),delNoteUseCase=get() )}
}