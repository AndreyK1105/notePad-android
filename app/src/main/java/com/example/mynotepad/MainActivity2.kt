package com.example.mynotepad

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynotepad.ui.dashboard.DashboardViewModel
import com.example.mynotepad.ui.theme.MyNotepadTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyNotepadTheme {

//                    Greeting(
//                        name = "MainActivity2  ",
//
//                    )

            }
        }
    }
}

@Composable
fun Greeting(vm: DashboardViewModel, name: String, modifier: Modifier = Modifier.fillMaxSize()) {
    val state by vm.uiState.collectAsState()
    Log.v("a", "MainActivity2 noteIndex= ${state.value?.size}")
    LazyColumn(modifier) {
        state.value?.let { items (it.size){ note ->

                Log.v("a", "MainActivity2 noteIndex=$note, textNote=${it[note].textNote}  ")
                Text(it[note].textNote)
             } }


    }
//    FloatingActionButton(onClick = { /*TODO*/ }) {
//
//    }
}
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNotepadTheme {
        Greeting("MainActivity2")
    }
}*/