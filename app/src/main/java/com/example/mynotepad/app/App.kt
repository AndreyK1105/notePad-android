package com.example.mynotepad.app

import android.app.Application
import com.example.mynotepad.di.dashboardModule
import com.example.mynotepad.di.dataBaseModule
import com.example.mynotepad.di.dataModule
import com.example.mynotepad.di.domainModule
import com.example.mynotepad.di.editDayModule
import com.example.mynotepad.di.editNoteModule
import com.example.mynotepad.di.editTodoModule
import com.example.mynotepad.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@App)
modules(listOf(
    dataBaseModule,
    dashboardModule,
    dataModule,
    domainModule,
    homeModule,
    editNoteModule,
    editDayModule,
    editTodoModule
))
        }
    }
}