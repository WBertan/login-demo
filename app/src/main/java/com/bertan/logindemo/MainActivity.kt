package com.bertan.logindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

sealed class Status(val text: String) {
    object Approved : Status("approved")
    object Rejected : Status("rejected")
}

class MainActivity : AppCompatActivity() {
    private val loginManager: LoginManager by lazy {
        (application as DI).getLoginManager()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {
            val status = authenticate()
            setStatus(status)
        }
    }

    private fun authenticate(): Status =
            when (loginManager.authenticate(getUser(), getPassword())) {
                true -> Status.Approved
                false -> Status.Rejected
            }

    private fun getUser(): String = user.text.toString()

    private fun getPassword(): String = password.text.toString()

    private fun setStatus(status: Status) = authStatus.apply { text = status.text.capitalize() }
}
