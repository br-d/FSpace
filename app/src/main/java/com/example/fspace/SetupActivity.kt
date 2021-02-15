package com.example.fspace

import android.app.ProgressDialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView

class SetupActivity : AppCompatActivity() {

    lateinit var fullnameEditText: EditText
    lateinit var usernameEditText: EditText
    lateinit var countryEditText: EditText
    lateinit var confirmButton: Button
    lateinit var profileCircleImageView: CircleImageView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var userDatabaseReference: DatabaseReference
    lateinit var setupProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        firebaseAuth = FirebaseAuth.getInstance()

        var currentUserId = firebaseAuth.currentUser?.uid
        userDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
        fullnameEditText = findViewById<EditText>(R.id.setupFullname)
        usernameEditText = findViewById<EditText>(R.id.setupUsername)
        countryEditText = findViewById<EditText>(R.id.setupCountry)
        confirmButton = findViewById<Button>(R.id.setupConfirmButton)
        profileCircleImageView = findViewById<CircleImageView>(R.id.setupProfilePicture)
        setupProgressDialog = ProgressDialog(this)
        confirmButton.setOnClickListener {
            saveUserInformation()
        }
    }

        private fun saveUserInformation() {
            var username = usernameEditText.text.toString()
            var fullName = fullnameEditText.text.toString()
            var country = countryEditText.text.toString()

            setupProgressDialog.setTitle("Setup account")
            setupProgressDialog.setMessage("Please wait while we setup you account information")
            setupProgressDialog.show()
            setupProgressDialog.setCanceledOnTouchOutside(true)

            if (username.isBlank() or fullName.isEmpty() or country.isEmpty())
                Toast.makeText(this, "Please fill all information", Toast.LENGTH_SHORT)
            else {
                var currentUserId = firebaseAuth.currentUser?.uid
                if (currentUserId != null) {
                    var userMap = HashMap<String, String>()
                    userMap.put("username", username)
                    userMap.put("fullname", fullName)
                    userMap.put("country", country)
                    userMap.put(
                        "status",
                        "Developing best apps in the Universe"
                    ) //Later changed via SetupActivity
                    userMap.put("gender", "none")
                    userMap.put("birthday", "01-01-2000")
                    userMap.put("relationshipStatus", "none")

                    userDatabaseReference.child(currentUserId).updateChildren(userMap as Map<String, Any>)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this@SetupActivity,
                                    "Your account was created successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                sendUserToMainActivity()
                                setupProgressDialog.dismiss()
                            } else {
                                Toast.makeText(
                                    this@SetupActivity,
                                    "Error occured: ${it.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                setupProgressDialog.dismiss()
                            }
                        }
                }
            }
        }

        private fun sendUserToMainActivity() {
            var mainIntent = Intent(this@SetupActivity, MainActivity::class.java)
            mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mainIntent)
            finish()
        }
}