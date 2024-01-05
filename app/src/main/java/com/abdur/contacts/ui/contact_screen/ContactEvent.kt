package com.abdur.contacts.ui.contact_screen

import com.abdur.contacts.data.Contact

sealed class ContactEvent{
    object OnAddContactClick : ContactEvent()
    data class OnContactClick(val contact: Contact) : ContactEvent()
    data class OnDeleteClick(val contact: Contact) : ContactEvent()
    data class OnSearchContact(val query : String) : ContactEvent()

}