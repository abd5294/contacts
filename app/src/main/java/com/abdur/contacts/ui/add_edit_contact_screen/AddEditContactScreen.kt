package com.abdur.contacts.ui.add_edit_contact_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdur.contacts.ui.theme.md_theme_dark_outline
import com.abdur.contacts.ui.theme.md_theme_dark_outlineVariant
import com.abdur.contacts.ui.theme.md_theme_light_onSecondaryContainer
import com.abdur.contacts.ui.theme.md_theme_light_secondaryContainer
import com.abdur.contacts.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContactScreen(
    onDone: () -> Unit,
    viewModel: AddEditContactViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                UiEvent.OnBackStack -> {
                    onDone()
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditContactEvent.OnSaveContact) },
                modifier = Modifier.padding(bottom = 8.dp, end = 8.dp),
                containerColor = md_theme_light_secondaryContainer,
                contentColor = md_theme_light_onSecondaryContainer,
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = viewModel.firstName,
                onValueChange = {
                    viewModel.onEvent(AddEditContactEvent.OnFirstNameChange(it))
                },
                maxLines = 1,
                placeholder = { Text(text = "Enter first name") },
                label = { Text(text = "First name") },
                textStyle = TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = md_theme_dark_outline,
                    unfocusedBorderColor = md_theme_dark_outlineVariant
                ),
                shape = RoundedCornerShape(12.dp),
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.lastName,
                onValueChange = {
                    viewModel.onEvent(AddEditContactEvent.OnLastNameChange(it))
                },
                maxLines = 1,
                placeholder = { Text(text = "Enter last name") },
                label = { Text(text = "Last Name") },
                textStyle = TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = md_theme_dark_outline,
                    unfocusedBorderColor = md_theme_dark_outlineVariant
                ),
                shape = RoundedCornerShape(12.dp),
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.phone,
                onValueChange = {
                    viewModel.onEvent(AddEditContactEvent.OnPhoneChange(it))
                },
                maxLines = 1,
                placeholder = { Text(text = "Enter phone") },
                label = { Text(text = "Phone") },
                textStyle = TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = md_theme_dark_outline,
                    unfocusedBorderColor = md_theme_dark_outlineVariant
                ),
                shape = RoundedCornerShape(12.dp),
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.location,
                onValueChange = {
                    viewModel.onEvent(AddEditContactEvent.OnLocationChange(it))
                },
                maxLines = 1,
                placeholder = { Text(text = "Enter location") },
                label = { Text(text = "Location") },
                textStyle = TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = md_theme_dark_outline,
                    unfocusedBorderColor = md_theme_dark_outlineVariant
                ),
                shape = RoundedCornerShape(12.dp),
            )
        }
    }
}