package com.bertan.logindemo

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginManagerImplSpec {
    private lateinit var loginManager: LoginManagerImpl

    @Before
    fun setUp() {
        loginManager = LoginManagerImpl()
    }

    @Test
    fun `given wrong user and password when authenticate it should return false`() {
        val user = "wrongUser"
        val password = "wrongPassword"

        assertFalse(loginManager.authenticate(user, password))
    }

    @Test
    fun `given wrong user and right password when authenticate it should return false`() {
        val user = "wrongUser"
        val password = "p123"

        assertFalse(loginManager.authenticate(user, password))
    }

    @Test
    fun `given right user and wrong password when authenticate it should return false`() {
        val user = "admin"
        val password = "wrongPassword"

        assertFalse(loginManager.authenticate(user, password))
    }

    @Test
    fun `given right user and password when authenticate it should return true`() {
        val user = "admin"
        val password = "p123"

        assertTrue(loginManager.authenticate(user, password))
    }

    @Test
    fun `given user when authenticate it should be case insensitive`() {
        val user = "ADMIN"
        val password = "p123"

        assertTrue(loginManager.authenticate(user, password))
    }

    @Test
    fun `given password when authenticate it should be case sensitive`() {
        val user = "admin"
        val password = "P123"

        assertFalse(loginManager.authenticate(user, password))
    }
}