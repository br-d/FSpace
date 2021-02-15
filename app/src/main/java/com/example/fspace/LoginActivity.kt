package com.example.fspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var redirectToRegisterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        redirectToRegisterTextView = findViewById<TextView>(R.id.loginLinkToRegistration)
        emailEditText = findViewById<EditText>(R.id.loginEmailEditText)
        passwordEditText = findViewById<EditText>(R.id.loginPasswordEditText)
        loginButton = findViewById<Button>(R.id.loginButton)

        redirectToRegisterTextView.setOnClickListener{
            sendUserToRegisterActivity()
        }
    }

    private fun sendUserToRegisterActivity() {
        var registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(registerIntent)
        finish()
    }

}