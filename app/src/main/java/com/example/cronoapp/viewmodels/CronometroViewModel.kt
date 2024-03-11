package com.example.cronoapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cronoapp.state.CronoState
import kotlinx.coroutines.Job

class CronometroViewModel: ViewModel() {
    var state by mutableStateOf(CronoState())
        private set

    var cronoJob by mutableStateOf<Job?>(null)
        private set

    var tiempo by mutableLongStateOf(0L)
        private set

    fun onValue(value:String){
        state = state.copy(title = value)
    }
}