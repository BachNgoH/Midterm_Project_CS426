import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.R

@Composable
fun ProfileScreen() {
    var currentView by remember { mutableStateOf<ProfileView>(ProfileView.Main) }
    var userProfile by remember {
        mutableStateOf(UserProfile("Victoria", "Yoker", "+380 12 345 67 89", "victoria.yoker@gmail.com"))
    }

    when (currentView) {
        is ProfileView.Main -> MainProfileScreen(
            userProfile = userProfile,
            onOptionSelected = { currentView = it },
            onProfilePictureChange = { /* Handle profile picture change */ }
        )
        is ProfileView.PersonalInfo -> PersonalInformationScreen(
            userProfile = userProfile,
            onProfileUpdate = { updatedProfile ->
                userProfile = updatedProfile
                currentView = ProfileView.Main
            },
            onBackClick = { currentView = ProfileView.Main },
            onProfilePictureChange = { /* Handle profile picture change */ }
        )

        else -> {}
    }
}

@Composable
fun MainProfileScreen(
    userProfile: UserProfile,
    onOptionSelected: (ProfileView) -> Unit,
    onProfilePictureChange: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AccountHeader(userProfile, onProfilePictureChange)
        Spacer(modifier = Modifier.height(24.dp))
        AccountOptions(onOptionSelected)
        Spacer(modifier = Modifier.weight(1f))
        EndSessionButton()
    }
}

@Composable
fun AccountHeader(userProfile: UserProfile, onProfilePictureChange: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Account",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
//            IconButton(
//                onClick = onProfilePictureChange,
//                modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .size(24.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFFFFA500))
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Edit,
//                    contentDescription = "Change Profile Picture",
//                    tint = Color.White
//                )
//            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${userProfile.firstName} ${userProfile.lastName}",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun AccountOptions(onOptionSelected: (ProfileView) -> Unit) {
    AccountOption(Icons.Default.Person, "Personal information") { onOptionSelected(ProfileView.PersonalInfo) }
    AccountOption(Icons.Default.ShoppingCart, "Payment and cards") { /* Handle other options */ }
    AccountOption(Icons.Default.Favorite, "Saved") { /* Handle other options */ }
    AccountOption(Icons.Default.AccountBox, "Booking history") { /* Handle other options */ }
    AccountOption(Icons.Default.Settings, "Settings") { /* Handle other options */ }
}

@Composable
fun AccountOption(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun EndSessionButton() {
    Button(
        onClick = { /* Handle end session */ },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(Color(0xFFFF3636))
    ) {
        Text("End session")
    }
}

sealed class ProfileView {
    object Main : ProfileView()
    object PersonalInfo : ProfileView()
}