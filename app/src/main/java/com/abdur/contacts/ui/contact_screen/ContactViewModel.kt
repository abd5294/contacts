package com.abdur.contacts.ui.contact_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdur.contacts.data.ContactRepository
import com.abdur.contacts.util.Routes
import com.abdur.contacts.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@HiltViewModel
class ContactViewModel(
    private val repository: ContactRepository
) : ViewModel() {

    private var _state = repository.getAllContacts()
    var state = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent

    fun onEvent(event : ContactEvent){
        when(event){
            is ContactEvent.OnAddContactClick -> {
                sendUiEvent(UiEvent.OnNavigate(Routes.ADD_EDIT_CONTACT.toString()))
            }

            is ContactEvent.OnContactClick -> {
                sendUiEvent(UiEvent.OnNavigate(Routes.ADD_EDIT_CONTACT.toString() + event.contact.id))
            }

            is ContactEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    repository.deleteContact(event.contact)
                }
            }
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}