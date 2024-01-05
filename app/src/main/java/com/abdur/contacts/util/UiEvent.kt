package com.abdur.contacts.util

sealed class UiEvent {

    data class OnNavigate(val route : String) : UiEvent()
    object OnBackStack : UiEvent()

    data class ShowSnackBar(val action : String, val message : String) : UiEvent()
}