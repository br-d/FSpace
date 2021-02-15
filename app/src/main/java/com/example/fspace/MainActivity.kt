package com.example.fspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        toolbar = findViewById(R.id.mainPageToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Home"

        drawerLayout = findViewById<DrawerLayout>(R.id.mainDrawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout,R.string.drawerOpen, R.string.drawerClose)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById<NavigationView>(R.id.mainNavigationView)
        var navigationView = navigationView.inflateHeaderView(R.layout.navigation_header)

        userMenuSelector()


    }

    fun userMenuSelector() {
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationProfile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationFriends -> {
                    Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationHome -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationLogout -> {
                    firebaseAuth.signOut()
                    sendToLoginActivity()
                    true
                }
                R.id.navigationMessages -> {
                    Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationPost -> {
                    Toast.makeText(this, "Post", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationSearchFriends -> {
                    Toast.makeText(this, "Search friend", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigationSettings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()

        var currentUser: FirebaseUser? = firebaseAuth.currentUser
        if(currentUser == null){
            sendToLoginActivity()
        }

    }

    private fun sendToLoginActivity() {
        var loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loginIntent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}