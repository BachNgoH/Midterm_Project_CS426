import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.R

data class UserProfile(
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInformationScreen(
    userProfile: UserProfile,
    onProfileUpdate: (UserProfile) -> Unit,
    onBackClick: () -> Unit,
    onProfilePictureChange: () -> Unit
) {
    var firstName by remember { mutableStateOf(userProfile.firstName) }
    var lastName by remember { mutableStateOf(userProfile.lastName) }
    var phone by remember { mutableStateOf(userProfile.phone) }
    var email by remember { mutableStateOf(userProfile.email) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Personal Information") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            ProfilePicture(onProfilePictureChange)
            Spacer(modifier = Modifier.height(24.dp))
            PersonalInfoField("First Name", firstName) { firstName = it }
            PersonalInfoField("Last Name", lastName) { lastName = it }
            PersonalInfoField("Phone", phone) { phone = it }
            PersonalInfoField("Email", email) { email = it }
            Spacer(modifier = Modifier.height(24.dp))
            SaveButton {
                val updatedProfile = UserProfile(firstName, lastName, phone, email)
                onProfileUpdate(updatedProfile)
            }
        }
    }
}

@Composable
fun ProfilePicture(onProfilePictureChange: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = onProfilePictureChange,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-8).dp, y = (-8).dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Change Photo",
                tint = Color(0xFFFFA500)
            )
        }
    }
}

@Composable
fun PersonalInfoField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        singleLine = true
    )
}

@Composable
fun SaveButton(onSave: () -> Unit) {
    Button(
        onClick = onSave,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
    ) {
        Text("Save changes", color = Color.White)
    }
}