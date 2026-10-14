package com.example.android_1sem

import kotlinx.serialization.Serializable

object Screens {
    @Serializable
    data object Auth

    @Serializable
    data class Profile(
        val email: String,
        val password: String
    )
}