package com.abdur.contacts.data

import kotlinx.coroutines.flow.Flow


class ContactRepository(private val dao : ContactDao) {

    suspend fun insertContact(contact: Contact){
        dao.insertContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        dao.deleteContact(contact)
    }

    suspend fun updateContact(contact: Contact){
        dao.updateContact(contact)
    }

    fun getAllContacts() : Flow<List<Contact>>{
        return dao.getAllContacts()
    }
}