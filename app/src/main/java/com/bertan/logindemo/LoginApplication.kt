package com.bertan.logindemo

import android.app.Application

interface DI {
    fun getLoginManager(): LoginManager
}

open class LoginApplication : Application(), DI {
    override fun getLoginManager(): LoginManager = LoginManagerImpl()
}