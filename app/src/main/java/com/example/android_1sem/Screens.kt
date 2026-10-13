package com.example.android_1sem

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object first : NavKey

@Serializable
data class second(val id: Int) : NavKey