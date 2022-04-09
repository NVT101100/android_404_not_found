/*
package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R

class SearchFragment:Fragment() {
    private var database = Firebase.database(Constants.databaseURL).reference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_search_blood,container,false)
        val searchView = view.findViewById<SearchView>(R.id.searchContent)
        searchData()
        return view
    }

    fun searchData():List<Users>{
        var users = ArrayList<Users>()
        database.child("user-list").get().addOnSuccessListener {
            var data = it.value.toString().split(",")
            for(i in 1 until(data.size)){
                var newUid = ""
                if(i == data.size-1) newUid = data[i].substring(1,data[i].length - 1)
                else newUid = data[i].substring(1,data[i].length)
                database.child("users").child(newUid).get().addOnSuccessListener {
                    var dt = it.value as HashMap<String,Any>
                    var dtt = dt["blood"] as HashMap<String,Any>
                    var user = Users(dt["fullname"].toString(),dt["age"].toString().toInt(),dt["address"].toString(),dt["gender"].toString(),dt["idNumber"].toString(),
                    dt["imageUri"].toString(),
                        Blood(Group.valueOf(dtt["bloodGroup"].toString()),dtt["summary"].toString(),dtt["goodHealth"].toString().toBoolean(),dtt["donated"].toString().toInt())
                    )
                   users.add(user)
                }
            }
        }
        return users
    }
}*/
