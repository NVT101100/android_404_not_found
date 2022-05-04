package com.nvt.bloodbank.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.dto.Chats

class ChatModel:ViewModel() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private val uid = Firebase.auth.currentUser?.uid
    private val _listMsg = MutableLiveData<List<Chats>>()
    val listMsg : LiveData<List<Chats>>
    get() = _listMsg
    private val _hopsId = MutableLiveData<String>()
    val hospId : LiveData<String>
    get() = _hopsId
    private val _msgCount = MutableLiveData<Int>()
    val msgCount  : LiveData<Int>
    get() = _msgCount
    fun init(){
        database.child("contacts/$uid/${_hopsId.value}").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var listmsg = snapshot.getValue<List<Chats>>()
                Log.d("check","$listmsg")
                _listMsg.value = listmsg!!
                _msgCount.value = snapshot.childrenCount.toInt()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("error","$error")
            }

        })
    }
    fun initHospId(hospId:String){
        _hopsId.value = hospId
    }
    fun init(list:List<Chats>){
        _listMsg.value = list
    }
}