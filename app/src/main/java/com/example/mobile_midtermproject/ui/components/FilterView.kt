import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.ui.theme.PrimaryColor

data class FilterState(
    val departureTimeRange: Int,
    val arrivalTimeRange: Int,
    val priceRange: ClosedFloatingPointRange<Float>,
    val selectedSortOption: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterView(
    initialState: FilterState,
    onBackPressed: () -> Unit,
    onApplyFilters: (FilterState) -> Unit
) {
    var currentState by remember { mutableStateOf(initialState) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filters") },
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
            Text("Departure", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            TimeRangeSelector(
                selectedIndex = currentState.departureTimeRange,
                onSelectionChanged = { currentState = currentState.copy(departureTimeRange = it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Arrival", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            TimeRangeSelector(
                selectedIndex = currentState.arrivalTimeRange,
                onSelectionChanged = { currentState = currentState.copy(arrivalTimeRange = it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Price", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            PriceRangeSlider(
                range = currentState.priceRange,
                onRangeChange = { currentState = currentState.copy(priceRange = it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Sort by", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            SortOptions(
                selectedOption = currentState.selectedSortOption,
                onOptionSelected = { currentState = currentState.copy(selectedSortOption = it) }
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { currentState = initialState },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Reset")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { onApplyFilters(currentState) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
                ) {
                    Text("Done", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun TimeRangeSelector(selectedIndex: Int, onSelectionChanged: (Int) -> Unit) {
    val timeRanges = listOf("12AM - 06AM", "06AM - 12PM", "12PM - 06PM")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        timeRanges.forEachIndexed { index, timeRange ->
            FilterChip(
                selected = selectedIndex == index,
                onClick = { onSelectionChanged(index) },
                label = { Text(timeRange) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = PrimaryColor,
                    selectedLabelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun PriceRangeSlider(range: ClosedFloatingPointRange<Float>, onRangeChange: (ClosedFloatingPointRange<Float>) -> Unit) {
    Column {
        RangeSlider(
            value = range,
            onValueChange = onRangeChange,
            valueRange = 50f..250f,
            colors = SliderDefaults.colors(
                thumbColor = PrimaryColor,
                activeTrackColor = PrimaryColor
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("From: $${range.start.toInt()}")
            Text("To: $${range.endInclusive.toInt()}")
        }
    }
}

@Composable
fun SortOptions(selectedOption: Int, onOptionSelected: (Int) -> Unit) {
    val options = listOf("Arrival time", "Departure time", "Price", "Lowest fare", "Duration")
    Column {
        options.forEachIndexed { index, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedOption == index,
                    onClick = { onOptionSelected(index) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = PrimaryColor
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(option)
            }
        }
    }
}