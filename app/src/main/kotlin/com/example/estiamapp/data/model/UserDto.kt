package com.example.estiamapp.data.model

import com.squareup.moshi.Json

data class UserDto (
    val id: Int,
    val email: String,
    @Json(name = "firstName") val firstName: String? = null,
    @Json(name = "lastName") val lastName: String? = null,
    val username: String? = null,
    val password: String = "",
    @Json(name = "image") val avatar: String = "",
    val role: String = "user",
    val creationAt: String = "",
    val updatedAt: String = ""
) {
    val name: String
        get() = if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()) {
            "$firstName $lastName"
        } else {
            username ?: email
        }
}