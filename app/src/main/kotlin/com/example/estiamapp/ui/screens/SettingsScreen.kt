package com.example.estiamapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estiamapp.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.estiamapp.ui.auth.AuthViewModel
import com.example.estiamapp.ui.components.LanguageDropdown
import com.example.estiamapp.ui.components.ThemeToggle
import com.example.estiamapp.ui.theme.EstiamAppTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    val authViewModel: AuthViewModel = viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.settings_title),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Appearance",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        ThemeToggle(modifier = Modifier.fillMaxWidth())

        Text(
            text = "Language",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        LanguageDropdown(modifier = Modifier.fillMaxWidth())

        Text(
            text = "Account",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = {
                authViewModel.logout()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD32F2F)
            )
        ) {
            Text(
                text = stringResource(id = R.string.logout),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Phone - Light",
    device = Devices.PHONE
)
@Composable
fun SettingsScreenPreviewLight() {
    EstiamAppTheme {
        SettingsScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Phone - Dark",
    device = Devices.PHONE,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SettingsScreenPreviewDark() {
    EstiamAppTheme {
        SettingsScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Tablet - Light",
    device = Devices.TABLET
)
@Composable
fun SettingsScreenPreviewTablet() {
    EstiamAppTheme {
        SettingsScreen()
    }
}
