package com.nvt.bloodbank.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R
import com.nvt.bloodbank.databinding.ActivityAddInfoBinding

class AddInfo : AppCompatActivity() {}
    /*private lateinit var binding: ActivityAddInfoBinding
    private var Auth : FirebaseAuth = Firebase.auth
    private var database : FirebaseDatabase = Firebase.database(Constants.databaseURL)
    private var storage = Firebase.storage
    lateinit var currentPhotoPath: String
    val IMAGE_REQUEST_CODE = 1_000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_add_info)
        binding.spinner.setEndIconOnClickListener {v:View ->
            showMenu(v,R.menu.pop_up_menu)
        }
        binding.submitProfile.setOnClickListener {
            val storageRef = storage.reference
            var imageUri = ""
            val fullname = binding.nameProfile.text.toString().trim()
            val address = binding.addressProfile.text.toString().trim()
            val age = binding.ageProfile.value.toInt()
            var gender = ""
            when(binding.genderGroup.checkedRadioButtonId) {
                binding.male.id -> gender = "male"
                binding.female.id -> gender = "female"
                binding.other.id -> gender = "other"
            }
            val bloodGroup = Group.valueOf(binding.optionView.text.toString())
            val summary = binding.summaryProfile.text.toString()
            val donated = binding.donatedProfile.value.toInt()
            val id = binding.idProfile.text.toString().trim()
            if(storageRef.child("profile/"+Auth.currentUser?.uid.toString()) != null){
               imageUri = "profile/"+Auth.currentUser?.uid
            }
            else imageUri = "default-avatar-boy.jpg"
            val blood = Blood(bloodGroup, summary,true, donated)
            val users = Users(fullname,age,address,gender,id,imageUri,blood)
            addInfo(users)
        }
        binding.btnAddImg.setOnClickListener {
            pickImageFromGallery()
        }
        binding.ageProfile.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            binding.ageValue.text = binding.ageProfile.value.toString()
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
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            val storageRef = storage.reference
            var file = data?.data
            val profileRef = storageRef.child("profile/"+Auth.currentUser?.uid.toString())
            if (file != null) {
                profileRef.putFile(file).addOnSuccessListener{
                    binding.profileImg.setImageURI(file)
                }
            }
        }
    }*/