import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.R

@Composable
fun BookingsScreen() {
    var currentView by remember { mutableStateOf<BookingView>(BookingView.Main) }

    when (currentView) {
        is BookingView.Main -> MainBookingsScreen(onOptionSelected = { currentView = it })
        is BookingView.HotelDetails -> TransportDetailsView(onBackPressed = { currentView = BookingView.Main })
        is BookingView.TransportDetails -> TransportDetailsView(onBackPressed = { currentView = BookingView.Main })
        else -> {}
    }
}

@Composable
fun MainBookingsScreen(onOptionSelected: (BookingView) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Booking",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        BookingOption(
            title = "Hotel",
            imageResId = R.drawable.hotel_illustration,
            description = "Find and book your perfect stay",
            onClick = { onOptionSelected(BookingView.HotelDetails) }
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        BookingOption(
            title = "Transport",
            imageResId = R.drawable.travel_illustration,
            description = "Book a transportation for your trip" ,
            onClick = { onOptionSelected(BookingView.TransportDetails) }

        )
    }
}

@Composable
fun BookingOption(
    title: String,
    imageResId: Int,
    description: String,
    onClick: () -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )

                TextButton(
                    onClick = onClick,
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    Text("BOOK", style = MaterialTheme.typography.labelLarge)
                }
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

sealed class BookingView {
    object Main : BookingView()
    object HotelDetails : BookingView()
    object TransportDetails : BookingView()
}