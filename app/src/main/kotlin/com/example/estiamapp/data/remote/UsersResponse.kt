package com.example.estiamapp.data.remote

import com.example.estiamapp.data.model.UserDto

data class UsersResponse(
    val users: List<UserDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

