package com.aleexalvz.cashwise.helper

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AuthHelperTest {

    //Testing validateEmail
    @Test
    fun `when pass valid email string then return true`() {
        listOf(
            "user@example.com",
            "user123@example.com.br",
            "user.name@domain.com",
            "user_name@domain.net"
        ).forEach { case ->
            val actual = AuthHelper.validateEmail(case)
            assertTrue(actual)
        }
    }

    @Test
    fun `when pass invalid email string then return false`() {
        listOf(
            "user@.com",
            "user@com",
            "user@domain",
            "user@domain.",
            "user@domain..com",
            "user@.domain.com",
            "@domain.com",
            "user@domaincom"
        ).forEach { case ->
            val actual = AuthHelper.validateEmail(case)
            assertFalse(actual)
        }
    }

    //Testing validatePassword
    @Test
    fun `when pass valid password string then return false`() {
        listOf(
            "Passw0rd",
            "StrongPassword123",
            "NoSpaces1",
            "6CharsA",
            "LongPasswordWithNumbers12345"
        ).forEach { password ->
            val actual = AuthHelper.validatePassword(password)
            assertTrue(actual)
        }
    }

    @Test
    fun `when pass invalid password string then return false`() {
        listOf(
            "Sho15",    // Doesn't have at least 6 chars
            "NoNumber",  // Doesn't have at least 1 number
            " WithSpace", // Has whitespaces
            " 123 456 ",  // Has whitespaces
            "    ",       // Has only whitespaces
            ""            // Empty string
        ).forEach { password ->
            val actual = AuthHelper.validatePassword(password)
            assertFalse(actual)
        }
    }
}