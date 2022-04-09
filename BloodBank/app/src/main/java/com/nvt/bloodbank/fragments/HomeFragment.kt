package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.R

class HomeFragment:Fragment(){
    private var Auth = Firebase.auth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_home,container,false)
    }

    override fun onStop() {
        super.onStop()
        if(Auth.currentUser!=null) Auth.signOut()
    }
}