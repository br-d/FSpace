package com.example.fspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import de.hdodenhof.circleimageview.CircleImageView

class SetupActivity : AppCompatActivity() {

    lateinit var fullnameEditText: EditText
    lateinit var usernameEditText: EditText
    lateinit var countryEditText: EditText
    lateinit var confirmButton: Button
    lateinit var profileCircleImageView: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        fullnameEditText = findViewById<EditText>(R.id.setupFullname)
        usernameEditText = findViewById<EditText>(R.id.setupUsername)
        countryEditText = findViewById<EditText>(R.id.setupCountry)
        confirmButton = findViewById<Button>(R.id.setupConfirmButton)
        profileCircleImageView = findViewById<CircleImageView>(R.id.setupProfilePicture)


    }
}