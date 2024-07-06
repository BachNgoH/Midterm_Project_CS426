import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.mobile_midtermproject.R

@Composable
fun HomeScreen(navController : NavHostController) {
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
        BookingServices(navController)
    }
}

@Composable
fun BookingServices(navController: NavHostController) {
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
            ServiceItem(
                "Trips",
                ImageVector.vectorResource(id = R.drawable.earth_icon),
                onClick = { navController.navigate(Screen.Home.route)})
            ServiceItem(
                "Hotel",
                ImageVector.vectorResource(id = R.drawable.hotel_icon),
                onClick = { navController.navigate(Screen.Home.route)})
            ServiceItem(
                "Transport",
                ImageVector.vectorResource(id = R.drawable.plane_icon),
                onClick = { navController.navigate(Screen.Bookings.route)})
            ServiceItem(
                "Events",
                ImageVector.vectorResource(id = R.drawable.events_icon),
                onClick = { navController.navigate(Screen.Home.route)}
            )
        }
    }
}

@Composable
fun ServiceItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(64.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = MaterialTheme.shapes.medium,
            onClick = onClick
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