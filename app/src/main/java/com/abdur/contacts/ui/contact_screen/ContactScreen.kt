package com.abdur.contacts.ui.contact_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdur.contacts.data.Contact
import com.abdur.contacts.ui.theme.md_theme_dark_outline
import com.abdur.contacts.ui.theme.md_theme_dark_outlineVariant
import com.abdur.contacts.ui.theme.md_theme_dark_primaryContainer
import com.abdur.contacts.ui.theme.md_theme_light_errorContainer
import com.abdur.contacts.ui.theme.md_theme_light_onSecondaryContainer
import com.abdur.contacts.ui.theme.md_theme_light_onSurface
import com.abdur.contacts.ui.theme.md_theme_light_onTertiary
import com.abdur.contacts.ui.theme.md_theme_light_onTertiaryContainer
import com.abdur.contacts.ui.theme.md_theme_light_primaryContainer
import com.abdur.contacts.ui.theme.md_theme_light_secondaryContainer
import com.abdur.contacts.util.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    onNavigate: (String) -> Unit,
) {
    val viewmodel: ContactViewModel = hiltViewModel()
    val contacts = viewmodel.state.collectAsState()


    LaunchedEffect(key1 = true) {
        viewmodel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNavigate -> {
                    onNavigate(event.route)
                }

                else -> Unit
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp, end = 8.dp),
        containerColor = Color.White,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewmodel.onEvent(ContactEvent.OnAddContactClick) },
                containerColor = md_theme_light_secondaryContainer,
                contentColor = md_theme_light_onSecondaryContainer
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add contact")
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            OutlinedTextField(
                value = viewmodel.query,
                onValueChange = { viewmodel.onEvent(ContactEvent.OnSearchContact(it)) },
                placeholder = { Text(text = "Search...") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = md_theme_dark_outline,
                    unfocusedBorderColor = md_theme_dark_outlineVariant
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(
                contentPadding = paddingValues,
            ) {
                items(contacts.value.size) { item ->
                    ContactItem(
                        contact = contacts.value[item],
                        onDelete = viewmodel::onEvent,
                        modifier = Modifier.clickable {
                            viewmodel.onEvent(ContactEvent.OnContactClick(contacts.value[item]))
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
