package com.knilim.knilim.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
        val userId: String,
        val nickname: String,
        val email: String,
        val phone: String,
        val password: String,
        val avatar: String,
        val signature: String
)
