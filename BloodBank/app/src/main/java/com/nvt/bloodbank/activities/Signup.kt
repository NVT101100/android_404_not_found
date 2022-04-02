package com.nvt.bloodbank.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.R
import com.nvt.bloodbank.models.Users
import java.util.regex.Pattern
import java.util.regex.Matcher
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.models.Blood
import com.nvt.bloodbank.models.Group


class Signup : AppCompatActivity() {
    private lateinit var newAuth : FirebaseAuth
    private lateinit var users : Users
    private val database : FirebaseDatabase = Firebase.database(Constants.databaseURL)
    override fun onCreate(savedInstanceState: Bundle?) {
        newAuth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val emailEditText : EditText = findViewById(R.id.emailSignup)
        val passEditText : EditText = findViewById(R.id.passwordSigup)
        val repassEditText :EditText = findViewById(R.id.rePasswordSigup)
        val signupBtn : Button = findViewById(R.id.btnSignup)

        var dialog = Dialog(this)
        var view = layoutInflater.inflate(R.layout.loading,null)
        dialog.setContentView(view)

        signupBtn.setOnClickListener {
            var email : String = emailEditText.text.toString().trim()
            var password :String = passEditText.text.toString().trim()
            var repassword :String = repassEditText.text.toString().trim()
            dialog.show()
            if(checkSignup(email,password,repassword,this)){
                newAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Authentication success.",
                                Toast.LENGTH_SHORT).show()
                            newAuth.currentUser?.sendEmailVerification()
                            dialog.dismiss()
                            createNewUser(newAuth.currentUser?.uid.toString());
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                    }
            }
            else {
                dialog.dismiss()
                passEditText.setText("")
                repassEditText.setText("")
            }
        }
    }

    private fun checkSignup(email:String,password:String,repass:String,context:Context):Boolean {
        val letter: Pattern = Pattern.compile("[A-Z]")
        val digit: Pattern = Pattern.compile("[0-9]")
        val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
        val hasLetter: Matcher = letter.matcher(password)
        val hasDigit: Matcher = digit.matcher(password)
        val hasSpecial: Matcher = special.matcher(password)

        if(!TextUtils.isEmpty(email)&&Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(password.equals(repass)){
                if(hasLetter.find() && hasDigit.find() && hasSpecial.find()&&password.length>8){
                       return true
                }
                else{
                    Toast.makeText(context,"Password must contain at least 8 character, an Uppercase, a numbers and a speacial character",Toast.LENGTH_LONG).show()
                    return false
                }
            }
            else{
                Toast.makeText(context,"Password doesn't match",Toast.LENGTH_SHORT).show()
                return false
            }
        }
        else{
            Toast.makeText(context,"Please enter an Email",Toast.LENGTH_SHORT).show()
            return false
        }
    }
    private fun createNewUser(Uid:String){
        users = Users("",0,"","","","", Blood(Group.Default,"",true,0))
        database.reference.child("users").child(Uid).setValue(users)
        startActivity(Intent(this,Login::class.java));
        finish()
    }

}