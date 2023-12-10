package com.abdur.contacts.util

sealed class UiEvent {

    data class OnNavigate(val route : String) : UiEvent()
    object OnBackStack : UiEvent()
}