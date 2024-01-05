package com.abdur.contacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    var firstName : String,
    var lastName : String,
    var phone : String,
    var location : String,

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
)