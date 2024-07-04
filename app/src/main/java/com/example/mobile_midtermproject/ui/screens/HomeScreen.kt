import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Explore the beautiful world!",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(24.dp))
        BookingServices()
    }
}

@Composable
fun BookingServices() {
    Column {
        Text(
            text = "Booking Services",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ServiceItem("Trips", Icons.Default.Done)
            ServiceItem("Hotel", Icons.Default.Done)
            ServiceItem("Transport", Icons.Default.Done)
            ServiceItem("Events", Icons.Default.FavoriteBorder)
        }
    }
}

@Composable
fun ServiceItem(text: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(64.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}