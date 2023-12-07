package com.abdur.contacts.data

import kotlinx.coroutines.flow.Flow


interface ContactRepository {

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    suspend fun updateContact(contact: Contact)

    fun getAllContacts() : Flow<List<Contact>>
}