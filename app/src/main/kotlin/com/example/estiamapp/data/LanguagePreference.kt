package com.example.estiamapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.os.LocaleListCompat

object LanguagePreference {
    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"
    
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    fun saveLanguage(context: Context, languageCode: String) {
        getSharedPreferences(context).edit()
            .putString(KEY_LANGUAGE, languageCode)
            .apply()
    }
    
    fun getSavedLanguage(context: Context): String {
        return getSharedPreferences(context).getString(KEY_LANGUAGE, "en") ?: "en"
    }
    
    fun applySavedLanguage(context: Context) {
        val savedLanguage = getSavedLanguage(context)
        val locales = LocaleListCompat.forLanguageTags(savedLanguage)
        androidx.appcompat.app.AppCompatDelegate.setApplicationLocales(locales)
    }
}

