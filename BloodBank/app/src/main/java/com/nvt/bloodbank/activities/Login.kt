package com.nvt.bloodbank.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.R

class Login : AppCompatActivity() {
    private var Auth : FirebaseAuth = Firebase.auth
    private lateinit var databaseRef : DatabaseReference
    private var firebaseDatabase : FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val textToSignup : TextView = findViewById(R.id.textToSignup)
        val btnLogin : Button = findViewById(R.id.btnLogin)
        val emailEditText: EditText = findViewById(R.id.textEmailLogin)
        val passEditText : EditText = findViewById(R.id.textPasswordLogin)
        var dialog = Dialog(this)
        var view = layoutInflater.inflate(R.layout.loading,null)
        dialog.setContentView(view)

        textToSignup.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
            finish()
        }
        btnLogin.setOnClickListener {
            var email : String = emailEditText.text.toString().trim()
            var password : String = passEditText.text.toString().trim()
            dialog.show()
            if(checkSigin(email,password,this)){
                Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){task ->
                    if(task.isSuccessful) {
                        dialog.dismiss()
                        gotoActivity(task);
                    }
                    else {
                        dialog.dismiss()
                        Toast.makeText(this,"Invalid email or password!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                dialog.dismiss()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        if(Auth.currentUser != null && Auth.currentUser?.isEmailVerified == true)
            startActivity(Intent(this,Maps::class.java));
    }

    private fun checkSigin(email:String,password:String,context: Context):Boolean{
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
    private fun gotoActivity(task : Task<AuthResult>){
        databaseRef  = firebaseDatabase.getReference("users")
        databaseRef.child(Auth.currentUser!!.uid).child("name").setValue("nguyen thoi")
    }
}