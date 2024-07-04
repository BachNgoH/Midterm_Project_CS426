package com.example.mobile_midtermproject.ui.screens

import androidx.compose.foundation.Image
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountHeader()
        Spacer(modifier = Modifier.height(24.dp))
        AccountOptions()
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
fun AccountOptions() {
    AccountOption(icon = Icons.Default.Person, text = "Personal information")
    AccountOption(icon = Icons.Default.ShoppingCart, text = "Payment and cards")
    AccountOption(icon = Icons.Default.Favorite, text = "Saved")
    AccountOption(icon = Icons.Default.AccountBox, text = "Booking history")
    AccountOption(icon = Icons.Default.Settings, text = "Settings")
}

@Composable
fun AccountOption(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
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