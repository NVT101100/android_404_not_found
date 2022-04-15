package com.nvt.bloodbank.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.dto.Events

class EventModel:ViewModel() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private val _listEvent = MutableLiveData<List<Events>>()
    val listEvent : LiveData<List<Events>>
    get() = _listEvent
    fun Init(){
        database.child("events").addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val event = snapshot.getValue<List<Events>>()
                _listEvent.value = event!!
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}