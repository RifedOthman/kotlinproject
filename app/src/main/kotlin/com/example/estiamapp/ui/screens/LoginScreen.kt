package com.example.estiamapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.estiamapp.ui.auth.AuthViewModel
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.estiamapp.ui.theme.EstiamAppTheme

@Composable
fun LoginScreen(onNavigateRegister: () -> Unit, onLoggedIn: () -> Unit) {
    val vm: AuthViewModel = viewModel()
    val isAuthenticated by vm.isAuthenticated.collectAsState()

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            onLoggedIn()
        }
    }
    
    fun validateEmail(email: String): String? {
        return if (email.isBlank()) {
            "Email is required"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Invalid email format"
        } else null
    }
    
    fun validatePassword(password: String): String? {
        return if (password.isBlank()) {
            "Password is required"
        } else if (password.length < 6) {
            "Password must be at least 6 characters"
        } else null
    }
    
    val isFormValid = emailError == null && passwordError == null && 
                     email.isNotBlank() && pass.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Updated title style
        Text(
            "Sign in",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFF6200EE),
                fontSize = 30.sp
            ),
            modifier = Modifier.padding(vertical = 24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { 
                email = it
                emailError = validateEmail(it)
            },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            isError = emailError != null,
            supportingText = emailError?.let { { Text(it) } }
        )
        OutlinedTextField(
            value = pass,
            onValueChange = { 
                pass = it
                passwordError = validatePassword(it)
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            isError = passwordError != null,
            supportingText = passwordError?.let { { Text(it) } }
        )

        Button(
            onClick = {
                emailError = validateEmail(email)
                passwordError = validatePassword(pass)
                if (emailError == null && passwordError == null) {
                    error = ""
                    vm.login(email, pass) { error = it }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
            shape = RoundedCornerShape(50),
            enabled = isFormValid
        ) {
            Text("Log In")
        }

        Text(
            "or",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Replace Facebook Login with Register Button
        Button(
            onClick = onNavigateRegister,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
            shape = RoundedCornerShape(50)
        ) {
            Text("Register")
        }

        Button(
            onClick = { /* Implement Google login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Text("Sign in with Google")
        }

        Text(error, color = MaterialTheme.colorScheme.error)

        // Register Button
        TextButton(
            onClick = onNavigateRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register", color = Color(0xFF6200EE))
        }
    }
}

@Preview(
    showBackground = true,
    name = "Phone - Light",
    device = Devices.PHONE
)
@Composable
fun LoginScreenPreviewLight() {
    EstiamAppTheme {
        LoginScreen(
            onNavigateRegister = {},
            onLoggedIn = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Phone - Dark",
    device = Devices.PHONE,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LoginScreenPreviewDark() {
    EstiamAppTheme {
        LoginScreen(
            onNavigateRegister = {},
            onLoggedIn = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Tablet - Light",
    device = Devices.TABLET
)
@Composable
fun LoginScreenPreviewTablet() {
    EstiamAppTheme {
        LoginScreen(
            onNavigateRegister = {},
            onLoggedIn = {}
        )
    }
}
