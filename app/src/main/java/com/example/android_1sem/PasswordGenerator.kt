package com.example.android_1sem

object PasswordGenerator {
    private const val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private const val digits = "0123456789"

    fun generate(length: Int) : String {
        require(length >= 8)
        val all = letters + digits
        val chars = mutableListOf(letters.random(), digits.random())

        repeat(length - chars.size) {
            chars += all.random()
        }
        chars.shuffle()
        return chars.joinToString("")
    }
}