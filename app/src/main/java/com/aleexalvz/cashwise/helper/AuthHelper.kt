package com.aleexalvz.cashwise.helper

import androidx.core.util.PatternsCompat

object AuthHelper {

    /**
     * Verify if email is valid
     * */
    fun validateEmail(email: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    /**
     * Verify if password has 3 conditions
     *   1- Has no whitespaces
     *   2- Has at least 6 chars
     *   3- Has at least 1 number
     *   */
    fun validatePassword(password: String): Boolean =
        hasNoWhitespace(password) &&
                hasSixChars(password) &&
                hasAtLeastOneNumber(password)


    private fun hasNoWhitespace(text: String): Boolean {
        val regex = Regex("\\S+$") //No whitespace allowed
        return text.matches(regex)
    }

    private fun hasAtLeastOneNumber(text: String): Boolean {
        val regex = Regex(".*\\d.*") //At least one number
        return text.matches(regex)
    }

    private fun hasSixChars(text: String): Boolean {
        val regex = Regex(".{6,}") //At least eight places
        return text.matches(regex)
    }
}