package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.nvt.bloodbank.R

class CovidCert:Fragment() {
    private val storageRef = FirebaseStorage.getInstance().reference.child("covid_certificate")
    private val auth = Firebase.auth.currentUser
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.covid_certificate,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storageRef.child("${auth?.uid}.png").downloadUrl.addOnSuccessListener {
            Glide.with(this).load(it).into( view.findViewById(R.id.certImg))
        }
    }
}