import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
//import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobile_midtermproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInformationScreen(onBackClick: () -> Unit) {
    var firstName by remember { mutableStateOf("Victoria") }
    var lastName by remember { mutableStateOf("Yoker") }
    var phone by remember { mutableStateOf("+380 12 345 67 89") }
    var email by remember { mutableStateOf("victoria.yoker@gmail.com") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Personal Information") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
//                colors = Color.White,
//                elevation = 0.dp
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
            ProfilePicture()
            Spacer(modifier = Modifier.height(24.dp))
            PersonalInfoField("First Name", firstName) { firstName = it }
            PersonalInfoField("Last Name", lastName) { lastName = it }
            PersonalInfoField("Phone", phone) { phone = it }
            PersonalInfoField("Email", email) { email = it }
            Spacer(modifier = Modifier.height(24.dp))
            SaveButton()
        }
    }
}

@Composable
fun ProfilePicture() {
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
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Change Photo",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-8).dp, y = (-8).dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(4.dp)
        )
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
fun SaveButton() {
    Button(
        onClick = { /* Handle save changes */ },
        modifier = Modifier.fillMaxWidth(),
//        colors = ButtonDefaults.buttonColors(backgroundColo = Color(0xFFFFA500))
    ) {
        Text("Save changes", color = Color.White)
    }
}
