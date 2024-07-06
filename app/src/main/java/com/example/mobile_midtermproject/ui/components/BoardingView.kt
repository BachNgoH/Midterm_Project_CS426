package com.example.mobile_midtermproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_midtermproject.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardingPassView(
    flight: Flight,
//    passengers: List<Passenger>,
    onBackPressed: () -> Unit
) {
    var passengers = List<Passenger>(2) {
        Passenger(name = "John Doe", ticketNumber = "AL100", ticketClass = "Economic", seatNumber = "15A")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Boarding Pass") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            passengers.forEach { passenger ->
                BoardingPass(flight, passenger)
                Spacer(modifier = Modifier.height(24.dp))
            }
            DownloadTicketButton()
        }
    }
}

@Composable
fun BoardingPass(flight: Flight, passenger: Passenger) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AirlineInfo(flight.origin, flight.number)
            Divider(modifier = Modifier.padding(vertical = 16.dp))
            FlightInfo(flight)
            Divider(modifier = Modifier.padding(vertical = 16.dp))
            PassengerInfo(passenger)
            Barcode()
        }
    }
}

@Composable
fun AirlineInfo(airline: String, flightNumber: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ticket_illustration),
            contentDescription = "Airline Logo",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0F0E9))
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "$airline Flight $flightNumber",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun FlightInfo(flight: Flight) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FlightInfoColumn("From", flight.origin, flight.departure)
        Icon(
            painter = painterResource(id = R.drawable.ic_airplane),
            contentDescription = "Flight",
            tint = Color(0xFFFFA500),
            modifier = Modifier.size(24.dp)
        )
        FlightInfoColumn("To", flight.destination, flight.departure)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FlightInfoColumn("Date", flight.date)
        FlightInfoColumn("Departure", flight.departure)
    }
}

@Composable
fun FlightInfoColumn(title: String, value: String, subValue: String? = null) {
    Column {
        Text(text = title, color = Color.Gray, fontSize = 12.sp)
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        if (subValue != null) {
            Text(text = subValue, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
fun PassengerInfo(passenger: Passenger) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FlightInfoColumn("Passenger", passenger.name)
        FlightInfoColumn("Ticket", passenger.ticketNumber)
        FlightInfoColumn("Class", passenger.ticketClass)
        FlightInfoColumn("Seat", passenger.seatNumber)
    }
}

@Composable
fun Barcode() {
    Image(
        painter = painterResource(id = R.drawable.barcode),
        contentDescription = "Barcode",
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 8.dp)
    )
}

@Composable
fun DownloadTicketButton() {
    Button(
        onClick = { /* Handle download */ },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
    ) {
        Text("Download ticket", color = Color.White)
    }
}