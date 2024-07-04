package com.example.mobile_midtermproject.ui.screens

import PersonalInformationScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.R

@Composable
fun ProfileScreen() {
    var currentView by remember { mutableStateOf<ProfileView>(ProfileView.Main) }

    when (currentView) {
        is ProfileView.Main -> MainProfileScreen(onOptionSelected = { currentView = it })
        is ProfileView.PersonalInfo -> PersonalInformationScreen(onBackClick = { currentView = ProfileView.Main })
    }
}

@Composable
fun MainProfileScreen(onOptionSelected: (ProfileView) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountHeader()
        Spacer(modifier = Modifier.height(24.dp))
        AccountOptions(onOptionSelected)
        Spacer(modifier = Modifier.weight(1f))
        EndSessionButton()
    }
}

@Composable
fun AccountHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Account",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        Box(
            modifier = Modifier
                .width(80.dp) // Set the width to clip
                .aspectRatio(1f) // Maintain aspect ratio (1:1 for square shape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop, // Crop to fill the box while preserving aspect ratio
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape) // Clip to circle shape
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Victoria Yoker",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun AccountOptions(onOptionSelected: (ProfileView) -> Unit) {
    AccountOption(Icons.Default.Person, "Personal information") { onOptionSelected(ProfileView.PersonalInfo) }
    AccountOption(Icons.Default.ShoppingCart, "Payment and cards") { onOptionSelected(ProfileView.PersonalInfo) }
    AccountOption(Icons.Default.Favorite, "Saved") { onOptionSelected(ProfileView.PersonalInfo) }
    AccountOption(Icons.Default.AccountBox, "Booking history") { onOptionSelected(ProfileView.PersonalInfo) }
    AccountOption(Icons.Default.Settings, "Settings") { onOptionSelected(ProfileView.PersonalInfo) }
}
@Composable
fun AccountOption(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun EndSessionButton() {
    Button(
        onClick = { /* Handle end session */ },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(Color(0xFFFF3636))
    ) {
        Text("End session")
    }
}

sealed class ProfileView {
    object Main : ProfileView()
    object PersonalInfo : ProfileView()
}