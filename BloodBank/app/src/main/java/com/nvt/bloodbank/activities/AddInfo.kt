package com.nvt.bloodbank.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R

class AddInfo : AppCompatActivity() {
    private var Auth : FirebaseAuth = Firebase.auth
    private var database : FirebaseDatabase = Firebase.database(Constants.databaseURL)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)
    }

    override fun onStop() {
        super.onStop()
        Auth.signOut()
    }
}