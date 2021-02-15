package com.example.fspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var confirmPasswordEditText: EditText
    lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEditText = findViewById<EditText>(R.id.registerEmailEditText)
        passwordEditText = findViewById<EditText>(R.id.registerPasswordEditText)
        confirmPasswordEditText = findViewById<EditText>(R.id.registerConfirmPasswordEditText)
        registerButton = findViewById<Button>(R.id.registerConfirmationButton)


    }
}