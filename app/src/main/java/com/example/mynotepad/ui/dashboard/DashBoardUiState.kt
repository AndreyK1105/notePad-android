package com.example.mynotepad.ui.dashboard

import com.example.domain.models.Note

data class DashBoardUiState (
    val notes: List<Note> =  listOf()
)