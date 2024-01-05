package com.abdur.contacts.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun getAllContacts() : Flow<List<Contact>>

    @Query("SELECT * FROM Contact WHERE id = :id")
    suspend fun getContactById(id : Int) : Contact

    @Query("SELECT * FROM Contact WHERE firstName like '%' || :firstName || '%'")
    suspend fun getContactByFirstName(firstName : String) : Contact?
}