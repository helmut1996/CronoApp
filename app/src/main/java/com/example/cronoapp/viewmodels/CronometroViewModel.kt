package com.example.cronoapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cronoapp.state.CronoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    fun iniciar(){
        state = state.copy(
            cronometroActivo = true
        )
    }

    fun pausar(){
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = true
        )
    }

    fun detener(){
        cronoJob?.cancel()
        tiempo = 0
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = false,
            showTextField = false
        )
    }

    fun showTextField(){
        state = state.copy(
            showTextField= true
        )
    }

    fun crono(){
        if (state.cronometroActivo){
            cronoJob?.cancel()
            cronoJob = viewModelScope.launch {
                while(true){
                    delay(1000)
                    tiempo += 1000
                }
            }
        }else{
            cronoJob?.cancel()
        }
    }
}