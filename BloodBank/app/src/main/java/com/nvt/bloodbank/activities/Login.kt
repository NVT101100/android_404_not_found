package com.nvt.bloodbank.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R
import com.nvt.bloodbank.databinding.ActivityLoginBinding

const val RELOAD : Int = 0
const val NEW_USER : Int = 1
const val OLD_USER : Int = 2
const val NO_USER : Int = 3
const val UNVERIFIED_USER : Int = 4
const val LOGIN_FAILED : Int = 5

class Login : AppCompatActivity() {
    private var Auth : FirebaseAuth = Firebase.auth
    private var database : FirebaseDatabase = Firebase.database(Constants.databaseURL)
    private lateinit var binding : ActivityLoginBinding
    private var loginStatus : Int = RELOAD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkStatus()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val textToSignup : TextView = findViewById(R.id.textToSignup)
        val btnLogin : Button = findViewById(R.id.btnLogin)

        textToSignup.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
            finish()
        }
        btnLogin.setOnClickListener {
            var email : String = binding.textEmailLogin.text.toString().trim()
            var password : String = binding.textPasswordLogin.text.toString().trim()
            checkSigin(email,password)
        }
    }

    private fun checkInput(email:String,password:String,context: Context):Boolean {
        if(!TextUtils.isEmpty(email)){
            if(!TextUtils.isEmpty(password)){
                return true
            }
            else {
                Toast.makeText(context,"Please enter password!",Toast.LENGTH_SHORT).show()
                return false
            }
        }
        else {
            Toast.makeText(context,"Please enter an email!",Toast.LENGTH_SHORT).show()
            return false
        }
    }
    private fun checkStatus(){
        database.reference.child("users").child(Auth.currentUser?.uid.toString())
            .child("fullname").get().addOnSuccessListener {
               if(Auth.currentUser != null) {
                   if (Auth.currentUser?.isEmailVerified != null && Auth.currentUser?.isEmailVerified == true) {
                       if (it.value == "") {
                           loginStatus = NEW_USER //new user have not any info
                       } else loginStatus = OLD_USER
                   } else loginStatus = UNVERIFIED_USER
               } else loginStatus = NO_USER
                doLogin()
        }
            .addOnFailureListener {
                Log.d("failed","status failed")
            }
    }

    private fun checkSigin(email:String,password: String) {
        if(checkInput(email,password,this)){
            Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){task ->
                if(task.isSuccessful) {
                    checkStatus()
                }
                else {
                    loginStatus = LOGIN_FAILED
                    doLogin()
                }
            }
        }
        else {
            loginStatus = RELOAD
        }
    }
    private fun doLogin() {
        val actMain = Intent(this,MainActivity::class.java)
        val actAddInfo = Intent(this,AddInfo::class.java)
        when(loginStatus) {
            RELOAD -> {

            }
            NEW_USER -> {
                startActivity(actAddInfo)
            }
            OLD_USER -> {
                startActivity(actMain)
            }
            UNVERIFIED_USER -> {
                Toast.makeText(this,"Accout Unverified",Toast.LENGTH_SHORT).show()
            }
            NO_USER -> {
                Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()
            }
            LOGIN_FAILED -> {
                Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}