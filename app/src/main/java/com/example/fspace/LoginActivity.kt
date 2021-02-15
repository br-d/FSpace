package com.example.fspace

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var redirectToRegisterTextView: TextView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var loginProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        redirectToRegisterTextView = findViewById<TextView>(R.id.loginLinkToRegistration)
        emailEditText = findViewById<EditText>(R.id.loginEmailEditText)
        passwordEditText = findViewById<EditText>(R.id.loginPasswordEditText)
        loginButton = findViewById<Button>(R.id.loginButton)
        loginProgressDialog = ProgressDialog(this)
        firebaseAuth = FirebaseAuth.getInstance()


        loginButton.setOnClickListener{
            loginUserToFSpace()
        }

        redirectToRegisterTextView.setOnClickListener{
            sendUserToRegisterActivity()
        }
    }

    private fun loginUserToFSpace() {
        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()

        loginProgressDialog.setTitle("Log in")
        loginProgressDialog.setMessage("Please wait while logging in to F-Space")
        loginProgressDialog.show()
        loginProgressDialog.setCanceledOnTouchOutside(true)

        if(email.isEmpty() or password.isEmpty()) {
            Toast.makeText(this, "Please provide all informations", Toast.LENGTH_SHORT).show()
            loginProgressDialog.dismiss()
        }
        else
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                if(it.isSuccessful) {
                    sendUserToMainActivity()
                    Toast.makeText(this, "You are logged in", Toast.LENGTH_SHORT).show()
                    loginProgressDialog.dismiss()
                }
                else{
                    var message = it.exception?.message
                    Toast.makeText(this, "Error occurred: $message", Toast.LENGTH_SHORT)
                    loginProgressDialog.dismiss()
                }
            }
    }

    private fun sendUserToMainActivity() {
        var mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(mainIntent)
        finish()
    }

    private fun sendUserToRegisterActivity() {
        var registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(registerIntent)
    }

}