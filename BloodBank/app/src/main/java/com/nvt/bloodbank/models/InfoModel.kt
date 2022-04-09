package com.nvt.bloodbank.models

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class InfoModel:ViewModel(){
    private val _errName = MutableLiveData<Boolean>()
    val errName : LiveData<Boolean>
    get() = _errName
    private val _errAddress = MutableLiveData<Boolean>()
    val errAddress : LiveData<Boolean>
    get() = _errAddress
    private val _errAge = MutableLiveData<Boolean>()
    val errAge : LiveData<Boolean>
    get() = _errAge
    private val _errPhone = MutableLiveData<Boolean>()
    val errPhone : LiveData<Boolean>
    get() = _errPhone
    private val _errGender = MutableLiveData<Boolean>()
    val errGender : LiveData<Boolean>
    get() = _errGender
    private val _errState = MutableLiveData<Boolean>()
    val errState : LiveData<Boolean>
    get() = _errState
    private val _errIdNum = MutableLiveData<Boolean>()
    val errIdNum : LiveData<Boolean>
    get() = _errIdNum
    private val _errIsrNum = MutableLiveData<Boolean>()
    val errIsrNum : LiveData<Boolean>
    get() = _errIsrNum
    fun checkInput(name:String,address:String,dob:String,phone:String,gender:String,state:Boolean?,idNum:String,isrNum:String){
        val letter: Pattern = Pattern.compile("[A-z]")
        val digit: Pattern = Pattern.compile("[0-9]")
        val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
        _errName.value = TextUtils.isEmpty(name)&&digit.matcher(name).find()&&special.matcher(name).find()
        _errAddress.value = TextUtils.isEmpty(address)&&digit.matcher(address).find()&&special.matcher(address).find()
        _errPhone.value = TextUtils.isEmpty(phone)&&letter.matcher(phone).find()&&special.matcher(phone).find()
        _errGender.value = TextUtils.isEmpty(gender)
        _errState.value = state==null
        _errIdNum.value = TextUtils.isEmpty(idNum)&&idNum.length<9&&idNum.length>20&&special.matcher(idNum).find()
        _errIsrNum.value = TextUtils.isEmpty(isrNum)&&isrNum.length<9&&isrNum.length>20&&special.matcher(isrNum).find()
    }
}