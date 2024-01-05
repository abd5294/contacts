package com.abdur.contacts.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow


class ContactRepository(private val dao : ContactDao) {

    suspend fun insertContact(contact: Contact){
        dao.insertContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        dao.deleteContact(contact)
    }


    fun getAllContacts() : Flow<List<Contact>>{
        return dao.getAllContacts()
    }

    suspend fun getContactById(id : Int) : Contact{
        return dao.getContactById(id)
    }

    suspend fun getContactByFirstName(firstName : String) : Contact?{
        return dao.getContactByFirstName(firstName)
    }
}