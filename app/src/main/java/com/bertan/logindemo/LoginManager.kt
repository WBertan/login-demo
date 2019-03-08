package com.bertan.logindemo

interface LoginManager {
    fun authenticate(user: String, password: String): Boolean
}