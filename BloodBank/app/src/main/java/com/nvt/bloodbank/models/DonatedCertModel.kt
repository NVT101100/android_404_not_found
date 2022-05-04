package com.nvt.bloodbank.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.dto.Certificates
import com.nvt.bloodbank.dto.Users

class DonatedCertModel {
    private val auth = Firebase.auth.currentUser
    private val database = Firebase.database(Constants.databaseURL).reference
    private val _listCert = MutableLiveData<List<Certificates>>()
    val listCert : LiveData<List<Certificates>>
    get() = _listCert
    private val _user = MutableLiveData<Users>()
    val user : LiveData<Users>
    get() =  _user
     fun init(){
         database.child("users/${auth?.uid}").addValueEventListener(object: ValueEventListener {
             override fun onCancelled(error: DatabaseError) {
                 print("failed")
             }

             override fun onDataChange(snapshot: DataSnapshot) {
                 _user.value = snapshot.getValue<Users>()
                 _listCert.value = _user.value?.certs
                 Log.d("check","${_listCert.value}")
             }
         })
     }
}