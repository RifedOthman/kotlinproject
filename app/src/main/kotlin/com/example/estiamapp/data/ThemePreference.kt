package com.example.estiamapp.data

import android.content.Context
import android.content.SharedPreferences

object ThemePreference {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_THEME = "selected_theme"
    private const val THEME_LIGHT = "light"
    private const val THEME_DARK = "dark"
    private const val THEME_SYSTEM = "system"
    
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    fun saveTheme(context: Context, theme: String) {
        getSharedPreferences(context).edit()
            .putString(KEY_THEME, theme)
            .apply()
    }
    
    fun getSavedTheme(context: Context): String {
        return getSharedPreferences(context).getString(KEY_THEME, THEME_SYSTEM) ?: THEME_SYSTEM
    }
    
    fun isDarkTheme(context: Context): Boolean? {
        return when (getSavedTheme(context)) {
            THEME_DARK -> true
            THEME_LIGHT -> false
            else -> null // System default
        }
    }
}

