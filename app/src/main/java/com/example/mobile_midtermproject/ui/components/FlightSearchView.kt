package com.example.mobile_midtermproject.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchView(
    onBackPressed: () -> Unit,
    onFilterPressed: () -> Unit,
    onFlightSelected: (Flight) -> Unit
) {
    Scaffold(
    topBar = {
        TopAppBar(
            title = { Text("Flights") },
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
                .background(Color(0xFFF0F0F0))
        ) {
            CalendarView()
            FlightCount(onFilterPressed = onFilterPressed)
            FlightList(onFlightSelected = onFlightSelected)
        }
    }
}

@Composable
fun CalendarView() {
    val days = listOf("TH", "FR", "SA", "SU", "MO", "TU", "WE")
    val dates = listOf("02", "03", "04", "05", "06", "07", "08")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        days.zip(dates).forEach { (day, date) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(day, fontSize = 12.sp, color = Color.Gray)
                Text(date, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FlightCount(onFilterPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "7 flights available New York to London",
            fontSize = 14.sp,
            color = Color.Gray
        )
        IconButton(onClick = onFilterPressed) {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Filter",
                tint = Color(0xFFFFA500)
            )
        }
    }
}

@Composable
fun FlightList(onFlightSelected: (Flight) -> Unit) {
    val flights = List(5) {
        Flight("New York", "London", "02 Jun", "9:00 AM", "$50", "NL-41")
    }

    LazyColumn {
        items(flights) { flight ->
            FlightCard(flight, onFlightSelected = { onFlightSelected(flight) })
        }
    }
}

@Composable
fun FlightCard(flight: Flight, onFlightSelected: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onFlightSelected),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(flight.origin, fontWeight = FontWeight.Bold)
                Text(flight.destination, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFDDA2A1))
                        .align(Alignment.CenterStart)
                )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFDDA2A1))
                        .align(Alignment.CenterEnd)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Date", fontSize = 12.sp, color = Color.Gray)
                    Text(flight.date, fontWeight = FontWeight.Bold)
                }
                Column {
                    Text("Departure", fontSize = 12.sp, color = Color.Gray)
                    Text(flight.departure, fontWeight = FontWeight.Bold)
                }
                Column {
                    Text("Price", fontSize = 12.sp, color = Color.Gray)
                    Text(flight.price, fontWeight = FontWeight.Bold)
                }
                Column {
                    Text("Number", fontSize = 12.sp, color = Color.Gray)
                    Text(flight.number, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

data class Flight(
    val origin: String,
    val destination: String,
    val date: String,
    val departure: String,
    val price: String,
    val number: String
)