package com.abdur.contacts.ui.contact_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdur.contacts.data.Contact
import com.abdur.contacts.ui.theme.md_theme_light_onPrimaryContainer
import com.abdur.contacts.ui.theme.md_theme_light_primaryContainer

@Composable
fun ContactItem(
    contact: Contact,
    onDelete: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x66ACECFF))
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier.padding(start = 12.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${contact.firstName} ${contact.lastName}",
                    fontSize = 20.sp,
                    color = md_theme_light_onPrimaryContainer
                )

            }
            Text(text = contact.phone, fontSize = 16.sp)
        }

        Column(
            modifier = modifier.padding(end = 12.dp)
        ) {
            Row {

                IconButton(onClick = { onDelete(ContactEvent.OnDeleteClick(contact)) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                }
            }

        }
    }
}
