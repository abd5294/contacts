package com.abdur.contacts.ui.add_edit_contact_screen

import com.abdur.contacts.data.Contact

sealed class AddEditContactEvent {

    data class OnFirstNameChange(val firstName : String) : AddEditContactEvent()
    data class OnLastNameChange(val lastName : String) : AddEditContactEvent()
    data class OnPhoneChange(val phone : String) : AddEditContactEvent()
    data class OnLocationChange(val location : String) : AddEditContactEvent()
    object OnSaveContact : AddEditContactEvent()
}