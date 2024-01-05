package com.abdur.contacts.ui.add_edit_contact_screen


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdur.contacts.data.Contact
import com.abdur.contacts.data.ContactRepository
import com.abdur.contacts.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AddEditContactViewModel @Inject constructor(
    private val repository: ContactRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var phone by mutableStateOf("")
    var location by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        val contactId = savedStateHandle.get<Int>("contactId")
        if (contactId != -1) {
            viewModelScope.launch {
                repository.getContactById(contactId!!).let {
                    firstName = it.firstName
                    lastName = it.lastName
                    phone = it.phone
                    location = it.location
                }
            }
        }
    }

    fun onEvent(event: AddEditContactEvent) {

        when (event) {
            is AddEditContactEvent.OnFirstNameChange -> {
                firstName = event.firstName
            }

            is AddEditContactEvent.OnLastNameChange -> {
                lastName = event.lastName
            }

            is AddEditContactEvent.OnLocationChange -> {
                location = event.location
            }

            is AddEditContactEvent.OnPhoneChange -> {
                phone = event.phone
            }

            is AddEditContactEvent.OnSaveContact -> {
                if (firstName.isEmpty() || lastName.isEmpty() || location.isEmpty() || phone.isEmpty()) {
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            "close", "Fields cannot be empty"
                        )
                    )
                }

                if (location == "sim" || location == "phone") {
                    viewModelScope.launch {
                        repository.insertContact(
                            Contact(
                                firstName,
                                lastName,
                                phone,
                                location
                            )
                        )
                    }
                } else {
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            action = "close",
                            message = "Location can either be 'sim' or 'phone'."
                        )
                    )
                }
                sendUiEvent(UiEvent.OnBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
