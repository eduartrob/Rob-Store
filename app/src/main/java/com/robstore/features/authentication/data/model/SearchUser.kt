package com.robstore.features.authentication.data.model

data class LoginRequest(
    val email: String,
    val password: String
)