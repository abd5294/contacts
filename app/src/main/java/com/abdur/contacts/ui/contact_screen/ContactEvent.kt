package com.abdur.contacts.ui.contact_screen

import com.abdur.contacts.data.Contact

sealed class ContactEvent(val contact: Contact) {
    abstract class OnAddContactClick(contact : Contact) : ContactEvent(contact)
    abstract class OnContactClick(contact: Contact) : ContactEvent(contact)
    abstract class OnDeleteClick(contact: Contact) : ContactEvent(contact)

}