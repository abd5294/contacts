package com.abdur.contacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val firstName : String,
    val lastName : String,
    val phone : Int,
    val location : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int
)