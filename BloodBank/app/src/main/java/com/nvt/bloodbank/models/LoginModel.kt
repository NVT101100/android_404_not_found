package com.nvt.bloodbank.models

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.LogState


class LoginModel:ViewModel() {
    private val _logState = MutableLiveData<LogState>()
    val logState : LiveData<LogState>
    get() = _logState
    private val _errEmail = MutableLiveData<Boolean>()
    val errEmail : LiveData<Boolean>
    get() = _errEmail
    private val _errPass = MutableLiveData<Boolean>()
    val errPass : LiveData<Boolean>
    get() = _errPass
    private var database = Firebase.database(Constants.databaseURL).reference

    private fun checkInput(email:String, password:String){
        if(TextUtils.isEmpty(email)) {
            _errEmail.value = true
        }
        else {
            _errEmail.value = false
            _errPass.value = TextUtils.isEmpty(password)
        }
    }
    fun checkState(){
        var auth = Firebase.auth.currentUser
        if(auth != null) {
            if(!auth!!.isEmailVerified) {
                _logState.value=LogState.unidentified
            }
            else database.child("users").child(auth!!.uid).get().addOnSuccessListener {
                if(it.value == null) _logState.value=LogState.logged_new
                else _logState.value=LogState.logged_old
            }.addOnFailureListener {
                _logState.value=LogState.failed
            }
        }
        else _logState.value=LogState.not_logged
    }
    fun doLogin(email:String,password:String){
        checkInput(email,password)
        if(_errEmail.value == false && _errPass.value == false) {
            Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                checkState()
            }.addOnFailureListener {
                _logState.value=LogState.failed
            }
        }
    }
}