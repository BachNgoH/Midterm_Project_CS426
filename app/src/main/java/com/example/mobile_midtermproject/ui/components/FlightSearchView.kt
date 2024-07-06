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



    val flights = listOf(
        Flight("New York", "London", "2024-07-06", "09:30", "$650", "BA178"),
        Flight("Tokyo", "Sydney", "2024-07-06", "23:45", "$780", "QF22"),
        Flight("Paris", "Rome", "2024-07-07", "14:15", "$210", "AZ324"),
        Flight("Dubai", "Singapore", "2024-07-07", "01:20", "$420", "EK404"),
        Flight("Los Angeles", "Chicago", "2024-07-08", "11:05", "$320", "UA846"),
        Flight("Berlin", "Moscow", "2024-07-08", "16:50", "$280", "SU2313"),
        Flight("Mumbai", "Bangkok", "2024-07-09", "20:30", "$310", "TG318"),
        Flight("São Paulo", "Buenos Aires", "2024-07-09", "08:45", "$390", "LA8012"),
        Flight("Amsterdam", "Barcelona", "2024-07-10", "13:10", "$180", "VY8318"),
        Flight("Hong Kong", "Seoul", "2024-07-10", "10:25", "$290", "KE608"),
        Flight("Toronto", "Vancouver", "2024-07-11", "15:40", "$270", "AC118"),
        Flight("Istanbul", "Athens", "2024-07-11", "07:55", "$160", "TK1845"),
        Flight("Mexico City", "Cancun", "2024-07-12", "12:30", "$150", "AM824"),
        Flight("Copenhagen", "Stockholm", "2024-07-12", "18:20", "$140", "SK1419"),
        Flight("Cairo", "Dubai", "2024-07-13", "22:05", "$330", "MS916"),
        Flight("Johannesburg", "Cape Town", "2024-07-13", "06:15", "$200", "SA317"),
        Flight("Zurich", "Vienna", "2024-07-14", "09:50", "$170", "OS564"),
        Flight("Dublin", "Edinburgh", "2024-07-14", "17:35", "$120", "EI3250"),
        Flight("San Francisco", "Seattle", "2024-07-14", "14:00", "$190", "AS1532"),
        Flight("Helsinki", "Oslo", "2024-07-14", "11:45", "$230", "AY913")
    )

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