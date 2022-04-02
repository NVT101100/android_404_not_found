package com.nvt.tuan4

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class MyModel : ViewModel() {
    private val _errEmail = MutableLiveData<String>()
    val errEmail : LiveData<String?> get() = _errEmail
    private val _errPassword = MutableLiveData<String>()
    val errPassword :LiveData<String?> get() = _errPassword
    private val _errName = MutableLiveData<String>()
    val errName : LiveData<String?> get() = _errName
    private var _isLogged = MutableLiveData<Boolean>()
    val isLogged : LiveData<Boolean>  get() =  _isLogged

    fun checkSignup(email : String,password:String,name:String){
        if(checkEmail(email)) _errEmail.postValue("")
        else {
            _errEmail.postValue("Invalid email")
        }
        if(checkPassword(password)) _errPassword.postValue("")
        else {
            _errPassword.postValue("Invalid password")
        }
        if(!checkName(name)) _errName.postValue("")
        else {
            _errName.postValue("Invalid name")
        }
    }

    private fun checkEmail(email:String):Boolean {
        return email.length > 0 && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun checkPassword(password: String):Boolean{
        return password.length in 6..10 && Pattern.compile("[A-z]").matcher(password).find()
                && Pattern.compile("[0-9]").matcher(password).find()
                && Pattern.compile("[!@#$%^&*()]").matcher(password).find()
    }
    private fun checkName(name: String):Boolean{
        return name.length < 0 && Pattern.compile("[0-9]!@#\$%&*()").matcher(name).find()
    }

    fun login(email:String,password:String) :Boolean{
        if(email == DataStore.email) {
            if(password != DataStore.password) {
                _errPassword.postValue("Invalid password")
                return false
            }
            else {
                _errPassword.postValue("")
               return true
            }
        }
        else {
            _errEmail.postValue("Invalid email")
            return false
        }

    }
}