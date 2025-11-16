package com.example.estiamapp.ui.components

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.estiamapp.data.ThemePreference

@Composable
fun ThemeToggle(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as? Activity
    
    val savedTheme = ThemePreference.isDarkTheme(context)
    val isDarkTheme = savedTheme ?: false
    
    var isDark by remember { mutableStateOf(isDarkTheme) }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dark Mode",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        
        Switch(
            checked = isDark,
            onCheckedChange = { checked ->
                isDark = checked
                ThemePreference.saveTheme(
                    context,
                    if (checked) "dark" else "light"
                )
                // Recreate activity to apply theme change
                activity?.recreate()
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}

