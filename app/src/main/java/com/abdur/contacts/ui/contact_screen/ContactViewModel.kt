package com.abdur.contacts.ui.contact_screen

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdur.contacts.data.Contact
import com.abdur.contacts.data.ContactRepository
import com.abdur.contacts.util.Routes
import com.abdur.contacts.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository,
) : ViewModel() {

    val contacts = getAllContacts()

    private var _state = MutableStateFlow<List<Contact>>(emptyList())
    var state = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var query by mutableStateOf("")
    private var job: Job? = null

    private fun getAllContacts() {
        viewModelScope.launch {
            repository.getAllContacts().collect {
                _state.value = it
            }
        }
    }

    fun onEvent(event: ContactEvent) {
        when (event) {
            is ContactEvent.OnAddContactClick -> {
                sendUiEvent(UiEvent.OnNavigate(Routes.ADD_EDIT_CONTACT.toString()))
            }

            is ContactEvent.OnContactClick -> {
                sendUiEvent(UiEvent.OnNavigate("${Routes.ADD_EDIT_CONTACT}?contactId=${event.contact.id}"))
            }

            is ContactEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    repository.deleteContact(event.contact)
                }
            }

            is ContactEvent.OnSearchContact -> {
                query = event.query
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500L)
                    val contact = repository.getContactByFirstName(query)
                    if (contact != null) {
                        _state.value = listOf(contact)
                    }
                }
                if (event.query.isEmpty()) {
                    job?.cancel()
                    getAllContacts()
                }
            }
        }
    }


    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}