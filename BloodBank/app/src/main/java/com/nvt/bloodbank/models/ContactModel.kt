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
import com.nvt.bloodbank.bldGrp
import com.nvt.bloodbank.dto.Hospitals

class ContactModel:ViewModel() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private val auth = Firebase.auth
    private  val _listContact = MutableLiveData<List<Hospitals>>()
    val listContac : LiveData<List<Hospitals>>
    get() = _listContact
     fun Init(){
         val child = database.child("hospitals").orderByChild("hospitalBlood/"+bldGrp.valueOf("APos").value+"/ready").ref
             child.orderByValue().equalTo(auth.currentUser?.uid).addListenerForSingleValueEvent(object:ValueEventListener{
                 override fun onDataChange(snapshot: DataSnapshot) {
                     //val hospitals = snapshot.getValue<List<Hospitals>>()
                     //_listContact.value = hospitals!!
                     Log.d("check","${snapshot.getValue()}")
                 }

                 override fun onCancelled(error: DatabaseError) {
                     TODO("Not yet implemented")
                 }

             })
     }
}