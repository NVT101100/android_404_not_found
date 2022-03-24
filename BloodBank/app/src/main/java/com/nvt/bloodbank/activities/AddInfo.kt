package com.nvt.bloodbank.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R
import com.nvt.bloodbank.databinding.ActivityAddInfoBinding
import com.nvt.bloodbank.models.Blood
import com.nvt.bloodbank.models.Group
import com.nvt.bloodbank.models.InfoModel
import com.nvt.bloodbank.models.Users

class AddInfo : AppCompatActivity() {
    private lateinit var binding: ActivityAddInfoBinding
    private var Auth : FirebaseAuth = Firebase.auth
    private var database : FirebaseDatabase = Firebase.database(Constants.databaseURL)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_add_info)
        binding.spinner.setEndIconOnClickListener {v:View ->
            showMenu(v,R.menu.pop_up_menu)
        }
        binding.submitProfile.setOnClickListener {
            val fullname = binding.nameProfile.text.toString().trim()
            val address = binding.addressProfile.text.toString().trim()
            val age = binding.ageProfile.value.toInt()
            val gender = binding.genderGroup.checkedRadioButtonId
            val bloodGroup = binding.optionView.text.toString()
            val summary = binding.summaryProfile.text.toString()
            val donated = binding.donatedProfile.value.toInt()
            val id = binding.idProfile.text.toString().trim()
            val blood = Blood(Group.Default, summary,true, donated)
            val users = Users(fullname=fullname, age = age,address,"",id,blood)
            addInfo(users)
        }
    }

    override fun onStop() {
        super.onStop()
        //ngAuth.signOut()
    }

    private fun showMenu(v: View,@MenuRes menuRes: Int) {
        val popup = PopupMenu(this,v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem : MenuItem ->
            itemSelect(menuItem)
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

    private fun itemSelect(menuItem: MenuItem):Boolean{
        findViewById<AutoCompleteTextView>(R.id.optionView).setText(menuItem.title)
        return true
    }
    private fun addInfo(users: Users){
        database.reference.child("users").child(Auth.currentUser!!.uid).setValue(users).addOnSuccessListener {
            startActivity(Intent(this,Maps::class.java))
        }
    }
}