package com.nvt.bloodbank.models

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignupModel:ViewModel() {
    private val _errEmail = MutableLiveData<Boolean>()
    val errEmail : LiveData<Boolean>
    get() = _errEmail
    private val _errPass = MutableLiveData<Boolean>()
    val errPass : LiveData<Boolean>
    get() = _errPass
    private val _errRepass = MutableLiveData<Boolean>()
    val errRepass :LiveData<Boolean>
    get() = _errRepass
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean>
    get() = _isSuccess

    fun checkInput(email:String,password:String,repassword:String){
        val letter: Pattern = Pattern.compile("[A-z]")
        val digit: Pattern = Pattern.compile("[0-9]")
        val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
        if(TextUtils.isEmpty(email)&&Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errEmail.value = true
        }
        else {
            _errEmail.value = false
            if(!TextUtils.isEmpty(password)&&letter.matcher(password).find()&&digit.matcher(password).find()&&special.matcher(password).find()&&password.length>8) {
                _errPass.value = false
                _errRepass.value = repassword != password
            }
            else _errPass.value = true
        }
    }
    fun checkSignup(email: String,password: String,repassword: String){
        checkInput(email,password,repassword)
        if(_errEmail.value==false&&_errPass.value==false&&_errRepass.value==false) {
            val newAuth = Firebase.auth
            newAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                _isSuccess.value = true
                newAuth.currentUser?.sendEmailVerification()
            }.addOnFailureListener {
                _isSuccess.value = false
            }
        }
    }
}