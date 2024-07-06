import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportDetailsView(
    onBackPressed: () -> Unit,
    onSearch: () -> Unit
) {
    val cities = listOf("New York (NYC)", "London (LDN)", "Paris (PAR)", "Tokyo (TYO)", "Sydney (SYD)")
    var fromCity by remember { mutableStateOf(cities[0]) }
    var toCity by remember { mutableStateOf(cities[1]) }
    var fromExpanded by remember { mutableStateOf(false) }
    var toExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = { Text("Transport Booking") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            )
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CityDropdown(
                    "From",
                    fromCity,
                    fromExpanded,
                    cities,
                    onExpandedChange = { fromExpanded = it },
                    onCitySelected = {
                        fromCity = it
                        if (it == toCity) {
                            toCity = cities.first { city -> city != it }
                        }
                    },
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        val temp = fromCity
                        fromCity = toCity
                        toCity = temp
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.swap_horizontal),
                        contentDescription = "Switch locations",
                        tint = Color(0xFFFFA500),
                    )
                }

                CityDropdown(
                    "To",
                    toCity,
                    toExpanded,
                    cities,
                    onExpandedChange = { toExpanded = it },
                    onCitySelected = {
                        toCity = it
                        if (it == fromCity) {
                            fromCity = cities.first { city -> city != it }
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            // Rest of the content...
            Row(modifier = Modifier.fillMaxWidth()) {
                DateField("Departure", "Jun 02, 2022", Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                DateField("Return", "Jun 12, 2022", Modifier.weight(1f))
            }

            PassengerAndLuggage()
            TransportClass()
            TransportType()

            Button(
                onClick = onSearch,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
            ) {
                Text("Search", color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDropdown(
    label: String,
    selectedCity: String,
    expanded: Boolean,
    cities: List<String>,
    onExpandedChange: (Boolean) -> Unit,
    onCitySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = onExpandedChange
        ) {
            TextField(
                value = selectedCity,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(city) },
                        onClick = {
                            onCitySelected(city)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DateField(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun PassengerAndLuggage() {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Passenger & Luggage", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* Handle passengers */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Passengers",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /* Handle time */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_children),
                    contentDescription = "Time",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /* Handle luggage */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pet),
                    contentDescription = "Luggage",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /* Handle special items */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_luggage),
                    contentDescription = "Special Items",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun TransportClass() {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Class", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            FilterChip(
                selected = true,
                onClick = { /* Handle economy selection */ },
                label = { Text("Economy") },
                modifier = Modifier.padding(end = 8.dp)
            )
            FilterChip(
                selected = false,
                onClick = { /* Handle business selection */ },
                label = { Text("Business") }
            )
        }
    }
}

@Composable
fun TransportType() {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Transport", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* Handle plane */ }) {
                Icon(Icons.Default.Done, contentDescription = "plan")
            }
            IconButton(onClick = { /* Handle ship */ }) {
                Icon(Icons.Default.Done, contentDescription = "ship")
            }
            IconButton(onClick = { /* Handle train */ }) {
                Icon(Icons.Default.Done, contentDescription = "train")
            }
            IconButton(onClick = { /* Handle bus */ }) {
                Icon(Icons.Default.Done, contentDescription = "bus")

            }
        }
    }
}