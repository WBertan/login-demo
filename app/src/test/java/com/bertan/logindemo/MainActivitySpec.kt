package com.bertan.logindemo

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

class TestApplication : LoginApplication() {
    private var mockLoginManager: LoginManager? = null

    fun setLoginManager(loginManager: LoginManager) {
        this.mockLoginManager = loginManager
    }

    override fun getLoginManager(): LoginManager =
        mockLoginManager ?: super.getLoginManager()
}

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class MainActivitySpec {
    @MockK
    lateinit var mockLoginManager: LoginManager

    private lateinit var activityController: ActivityController<MainActivity>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        (ApplicationProvider.getApplicationContext() as TestApplication).setLoginManager(mockLoginManager)
        activityController = buildActivity(MainActivity::class.java)
        activityController.setup()
    }

    @Test
    fun `given authentication is successful when click to login it should show approved status`() {
        every { mockLoginManager.authenticate(any(), any()) } returns true

        val userView = activityController.get().findViewById<EditText>(R.id.user)
        val passwordView = activityController.get().findViewById<EditText>(R.id.password)
        val loginView = activityController.get().findViewById<Button>(R.id.login)
        val authStatusView = activityController.get().findViewById<TextView>(R.id.authStatus)

        userView.setText("dummyUser")
        passwordView.setText("dummyPassword")

        loginView.performClick()

        verify { mockLoginManager.authenticate("dummyUser", "dummyPassword") }
        assertEquals("Approved", authStatusView.text)
    }

    @Test
    fun `given authentication is rejected when click to login it should show rejected status`() {
        every { mockLoginManager.authenticate(any(), any()) } returns false

        val userView = activityController.get().findViewById<EditText>(R.id.user)
        val passwordView = activityController.get().findViewById<EditText>(R.id.password)
        val loginView = activityController.get().findViewById<Button>(R.id.login)
        val authStatusView = activityController.get().findViewById<TextView>(R.id.authStatus)

        userView.setText("dummyUser")
        passwordView.setText("dummyPassword")

        loginView.performClick()

        verify { mockLoginManager.authenticate("dummyUser", "dummyPassword") }
        assertEquals("Rejected", authStatusView.text)
    }
}