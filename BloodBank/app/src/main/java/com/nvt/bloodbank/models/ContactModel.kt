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
import com.nvt.bloodbank.dto.AppearContact
import com.nvt.bloodbank.dto.Hospitals
import com.rd.animation.data.Value

class ContactModel : ViewModel() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private val auth = Firebase.auth.currentUser
    private val _listContact = MutableLiveData<List<AppearContact>>()
    val listContact: LiveData<List<AppearContact>>
        get() = _listContact
    private val _listHospId = MutableLiveData<List<String>>()
    val listHospId : LiveData<List<String>>
    get() = _listHospId

    fun Init() {
        database.child("contacts").child(auth!!.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var listResult = ArrayList<String>()
                    snapshot.children.forEach {
                        if(it.key != null) listResult.add(it.key!!)
                    }
                    _listHospId.value = listResult
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
    fun getListHosp(){
        var contacts =  ArrayList<AppearContact>()
        for(s:String in _listHospId.value!!){
            var contact = AppearContact()
            contact.hospId = s
            database.child("hospitals/$s/hospitalName").get().addOnSuccessListener {
                contact.hospName = it.value.toString()
                contacts.add(contact)
                _listContact.value = contacts
            }
        }
    }
}