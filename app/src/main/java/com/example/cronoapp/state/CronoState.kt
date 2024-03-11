package com.example.cronoapp.state

data class CronoState(
    val cronometroActivo: Boolean = false,
    val showSaveButton: Boolean = false,
    val ShowTextField: Boolean = false,
    val title : String = ""
)
