package com.example.mobile_midtermproject.ui.icons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.R

@Composable
fun TripIcon() {
    Icon(
        painter = painterResource(id = R.drawable.trips_icon),
        contentDescription = "Description of your icon",
        modifier = Modifier.size(24.dp),
        tint = Color.Unspecified // Use this to keep the original colors of the SVG
    )
}