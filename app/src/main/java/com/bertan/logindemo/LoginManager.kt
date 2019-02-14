package com.bertan.logindemo

class LoginManager() {
    fun authenticate(user: String, password: String): Boolean =
            isValidUser(user) && isValidPassword(password)

    private fun isValidUser(user: String): Boolean =
            user.equals("admin", true)

    private fun isValidPassword(password: String): Boolean =
            password.equals("p123")
}