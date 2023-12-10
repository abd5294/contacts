package com.abdur.contacts.ui.add_edit_contact_screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle

@Composable
fun AddEditContactScreen(
    savedStateHandle: SavedStateHandle
) {
    val contactId : Int = checkNotNull(savedStateHandle["contactId"])

}