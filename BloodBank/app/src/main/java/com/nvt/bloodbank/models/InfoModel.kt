package com.nvt.bloodbank.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoModel:ViewModel(){
    private var _user = MutableLiveData<Users>()
    val user : LiveData<Users>
    get() = _user
}