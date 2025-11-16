package com.example.estiamapp

import android.util.Patterns
import org.junit.Assert.*
import org.junit.Test

class ValidationTest {
    
    @Test
    fun `email validation should return null for valid email`() {
        val validEmails = listOf(
            "test@example.com",
            "user.name@domain.co.uk",
            "user+tag@example.com"
        )
        
        validEmails.forEach { email ->
            val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
            assertTrue("Email $email should be valid", isValid)
        }
    }
    
    @Test
    fun `email validation should return error for invalid email`() {
        val invalidEmails = listOf(
            "invalid",
            "@example.com",
            "test@",
            "test@.com",
            "test..test@example.com"
        )
        
        invalidEmails.forEach { email ->
            val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
            assertFalse("Email $email should be invalid", isValid)
        }
    }
    
    @Test
    fun `password validation should require at least 6 characters`() {
        val shortPasswords = listOf("", "1", "12", "123", "1234", "12345")
        val validPasswords = listOf("123456", "password", "P@ssw0rd", "abcdefgh")
        
        shortPasswords.forEach { password ->
            assertTrue("Password '$password' should be invalid (length < 6)", password.length < 6)
        }
        
        validPasswords.forEach { password ->
            assertTrue("Password '$password' should be valid (length >= 6)", password.length >= 6)
        }
    }
    
    @Test
    fun `email validation helper function test`() {
        fun validateEmail(email: String): String? {
            return if (email.isBlank()) {
                "Email is required"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                "Invalid email format"
            } else null
        }
        
        assertEquals("Email is required", validateEmail(""))
        assertEquals("Invalid email format", validateEmail("invalid"))
        assertNull(validateEmail("test@example.com"))
    }
    
    @Test
    fun `password validation helper function test`() {
        fun validatePassword(password: String): String? {
            return if (password.isBlank()) {
                "Password is required"
            } else if (password.length < 6) {
                "Password must be at least 6 characters"
            } else null
        }
        
        assertEquals("Password is required", validatePassword(""))
        assertEquals("Password must be at least 6 characters", validatePassword("12345"))
        assertNull(validatePassword("123456"))
    }
}

