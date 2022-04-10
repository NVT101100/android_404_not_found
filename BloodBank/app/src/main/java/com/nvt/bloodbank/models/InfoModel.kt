package com.nvt.bloodbank.models

import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Year
import java.util.*
import java.util.regex.Pattern

class InfoModel:ViewModel(){
    //private val initial = false
    private val _errName = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errName : LiveData<Boolean>
    get() = _errName
    private val _errAddress = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errAddress : LiveData<Boolean>
    get() = _errAddress
    private val _errAge = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errAge : LiveData<Boolean>
    get() = _errAge
    private val _errPhone = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errPhone : LiveData<Boolean>
    get() = _errPhone
    private val _errGender = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errGender : LiveData<Boolean>
    get() = _errGender
    private val _errState = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errState : LiveData<Boolean>
    get() = _errState
    private val _errIdNum = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errIdNum : LiveData<Boolean>
    get() = _errIdNum
    private val _errIsrNum = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val errIsrNum : LiveData<Boolean>
    get() = _errIsrNum
    private val _isComplete = MutableLiveData<Boolean>()//.apply { postValue(initial) }
    val isComplete : LiveData<Boolean>
        get() = _isComplete
    fun checkInput(name:String, address:String, dob:String, phone:String, gender:String, state:Boolean?, idNum:String, isrNum:String){
        val letter: Pattern = Pattern.compile("[A-z]")
        val digit: Pattern = Pattern.compile("[0-9]")
        val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
        _errName.value = TextUtils.isEmpty(name)||digit.matcher(name).find()||special.matcher(name).find()
        _errAddress.value = TextUtils.isEmpty(address)||digit.matcher(address).find()||special.matcher(address).find()
        _errPhone.value = TextUtils.isEmpty(phone)||letter.matcher(phone).find()||special.matcher(phone).find()
        _errGender.value = TextUtils.isEmpty(gender)
        _errState.value = state==null
        _errIdNum.value = TextUtils.isEmpty(idNum)|| idNum.length !in 8..21 ||special.matcher(idNum).find()
        _errIsrNum.value = TextUtils.isEmpty(isrNum)|| isrNum.length !in 8..21 ||special.matcher(isrNum).find()
        val date = dob.split("/")
        val year = Calendar.getInstance().weekYear
        _errAge.value = year-date[2].toInt()<15
        _isComplete.value = !_errName.value!! &&!_errAddress.value!!
                && !_errAge.value!! && !_errGender.value!! && !_errIdNum.value!!
                && !_errIsrNum.value!! && !_errPhone.value!! && !_errState.value!!
        Log.d("check","${_isComplete.value}")
    }
}