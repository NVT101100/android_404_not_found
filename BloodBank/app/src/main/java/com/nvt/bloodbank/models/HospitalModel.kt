package com.nvt.bloodbank.models

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
import com.nvt.bloodbank.dto.Hospitals

class HospitalModel:ViewModel() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private  val _listHospital = MutableLiveData<List<Hospitals>>()
    val listHospital : LiveData<List<Hospitals>>
    get() = _listHospital
    fun Init(){
        database.child("hospitals").addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val hospital = snapshot.getValue<List<Hospitals>>()
                _listHospital.value = hospital!!
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}