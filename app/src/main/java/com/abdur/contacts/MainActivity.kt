package com.abdur.contacts

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abdur.contacts.ui.add_edit_contact_screen.AddEditContactScreen
import com.abdur.contacts.ui.contact_screen.ContactScreen
import com.abdur.contacts.ui.theme.ContactsTheme
import com.abdur.contacts.util.Routes
import com.abdur.contacts.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME_SCREEN.toString()
                ) {
                    composable(Routes.HOME_SCREEN.toString()) {
                        ContactScreen{ navController.navigate(it) }
                    }
                    composable(
                        "${Routes.ADD_EDIT_CONTACT}?contactId={contactId}",
                        arguments = listOf(
                            navArgument("contactId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditContactScreen(onDone = {navController.popBackStack()})
                    }
                }
            }
        }
    }
}
