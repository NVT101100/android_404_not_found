package com.nvt.bloodbank.models

import android.location.Location
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
import com.nvt.bloodbank.dto.Hospitals
import com.nvt.bloodbank.dto.Position
import com.nvt.bloodbank.dto.Users
import kotlin.Comparator

class HospitalModel : ViewModel() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private var dbList: List<Hospitals> = listOf()
    private lateinit var user: Users
    private val auth = Firebase.auth.currentUser
    private val _listHospital = MutableLiveData<List<Hospitals>>()
    val listHospital: LiveData<List<Hospitals>>
        get() = _listHospital

    fun search(stringSearch: String) {
        if (stringSearch.uppercase() == "ALL") _listHospital.value = dbList
        else database.child("hospitals").orderByChild("hospitalName").equalTo(stringSearch)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                   snapshot.children.forEach {
                       val hospital = it.getValue<Hospitals>()
                       if(hospital!=null) _listHospital.value = listOf(hospital)
                   }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun Init() {
        database.child("users").child(auth!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    user = snapshot.getValue<Users>()!!
                    getInitList()
                }
            })
    }
    fun getInitList(){
        database.child("hospitals").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val hospital = snapshot.getValue<List<Hospitals>>()
                dbList = hospital!!
                val hospitalComp = Hospitals()
                hospitalComp.hospitalPosition.latitude = user.position.latitude
                hospitalComp.hospitalPosition.longitude = user.position.longitude
                dbList = dbList.sortedBy {hospCompare.compare(it,hospitalComp)}
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
    class hospCompare{
        companion object:Comparator<Hospitals>{
            override fun compare(p0: Hospitals?, p1: Hospitals?): Int {
                val result0 = FloatArray(1)
                Location.distanceBetween(
                    p1?.hospitalPosition!!.latitude,
                    p1.hospitalPosition.longitude,
                    p0?.hospitalPosition!!.latitude,
                    p0.hospitalPosition.longitude,
                    result0
                )
                return if(result0[0]>8000) 1
                else 0
            }
        }
    }
    fun resetList(){
        _listHospital.value = listOf()
    }
}