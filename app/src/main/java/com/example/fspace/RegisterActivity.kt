package com.example.fspace

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var confirmPasswordEditText: EditText
    lateinit var registerButton: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var registerBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        emailEditText = findViewById<EditText>(R.id.registerEmailEditText)
        passwordEditText = findViewById<EditText>(R.id.registerPasswordEditText)
        confirmPasswordEditText = findViewById<EditText>(R.id.registerConfirmPasswordEditText)
        registerButton = findViewById<Button>(R.id.registerConfirmationButton)
        registerBar = ProgressDialog(this)

        registerButton.setOnClickListener{
            createNewUserAccount()
        }
    }

    private fun createNewUserAccount() {
        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()
        var confirmPassword = confirmPasswordEditText.text.toString()

        if(email.isBlank() or password.isEmpty() or confirmPassword.isEmpty())
            Toast.makeText(this, "Please enter all needed information", Toast.LENGTH_SHORT).show()
        else if(password != confirmPassword)
            Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show()
        else{
            registerBar.setTitle("Creating an account")
            registerBar.setMessage("Please wait while we are creating your account")
            registerBar.show()
            registerBar.setCanceledOnTouchOutside(true)

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{
                        if(it.isSuccessful) {
                            Toast.makeText(this, "Your account has been created", Toast.LENGTH_SHORT).show()
                            registerBar.dismiss()
                            sendToSetupActivity()
                        }
                        else {
                            registerBar.dismiss()
                            var message = it.exception?.message
                            Toast.makeText(this, "Error occurred: $message", Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }

    private fun sendToSetupActivity() {
        var setupIntent = Intent(this@RegisterActivity, SetupActivity::class.java)
        setupIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(setupIntent)
        finish()
    }
}