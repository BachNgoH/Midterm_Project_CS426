package com.example.mobile_midtermproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatSelectionView(
    onBackPressed: () -> Unit,
    flight: Flight
) {
    var selectedSeat by remember { mutableStateOf("") }
    var totalPrice by remember { mutableStateOf(50.00) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Seats") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Traveller", fontWeight = FontWeight.Bold)
            SeatLegend()
            Spacer(modifier = Modifier.height(16.dp))
            SeatGrid(onSeatSelected = { seat ->
                selectedSeat = seat
                // Update price logic here
            })
            Spacer(modifier = Modifier.weight(1f))
            BottomInfo(selectedSeat, totalPrice)
        }
    }
}

@Composable
fun SeatLegend() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LegendItem(color = Color(0xFFFFA500), text = "Selected")
        LegendItem(color = Color(0xFF4CAF50), text = "Booked")
        LegendItem(color = Color(0xFFE0E0E0), text = "Available")
    }
}

@Composable
fun LegendItem(color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color, RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun SeatGrid(onSeatSelected: (String) -> Unit) {
    val seatStatus = remember {
        mutableStateMapOf(
            "A1" to SeatStatus.BOOKED, "B1" to SeatStatus.BOOKED,
            "A2" to SeatStatus.SELECTED, "B2" to SeatStatus.AVAILABLE,
            "C1" to SeatStatus.BOOKED, "D1" to SeatStatus.AVAILABLE,
            "C2" to SeatStatus.AVAILABLE, "D2" to SeatStatus.BOOKED
            // Add more seats as needed
        )
    }

    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("A", fontWeight = FontWeight.Bold)
            Text("B", fontWeight = FontWeight.Bold)
            Text("", modifier = Modifier.width(20.dp)) // Gap
            Text("C", fontWeight = FontWeight.Bold)
            Text("D", fontWeight = FontWeight.Bold)
        }
        repeat(6) { row ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SeatItem(seatStatus, "A${row + 1}", onSeatSelected)
                SeatItem(seatStatus, "B${row + 1}", onSeatSelected)
                Text("${row + 1}", modifier = Modifier.width(20.dp), textAlign = TextAlign.Center)
                SeatItem(seatStatus, "C${row + 1}", onSeatSelected)
                SeatItem(seatStatus, "D${row + 1}", onSeatSelected)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

enum class SeatStatus { AVAILABLE, BOOKED, SELECTED }

@Composable
fun SeatItem(
    seatStatus: MutableMap<String, SeatStatus>,
    seatNumber: String,
    onSeatSelected: (String) -> Unit
) {
    val status = seatStatus[seatNumber] ?: SeatStatus.AVAILABLE
    val color = when (status) {
        SeatStatus.AVAILABLE -> Color(0xFFE0E0E0)
        SeatStatus.BOOKED -> Color(0xFF4CAF50)
        SeatStatus.SELECTED -> Color(0xFFFFA500)
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable(
                enabled = status == SeatStatus.AVAILABLE,
                onClick = {
                    seatStatus[seatNumber] = SeatStatus.SELECTED
                    onSeatSelected(seatNumber)
                }
            )
    )
}

@Composable
fun BottomInfo(selectedSeat: String, totalPrice: Double) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Your seats")
            Text("Traveller 1 / Seat $selectedSeat")
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total price")
            Text("$${String.format("%.2f", totalPrice)}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle continue */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
        ) {
            Text("Continue")
        }
    }
}