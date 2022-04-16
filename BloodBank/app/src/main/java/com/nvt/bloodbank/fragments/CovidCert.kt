package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.nvt.bloodbank.R

class CovidCert:Fragment() {
    private val storageRef = FirebaseStorage.getInstance().reference.child("covid_certificate")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.covid_certificate,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storageRef.child("test.png").downloadUrl.addOnSuccessListener {
            Glide.with(this).load(it).into( view.findViewById(R.id.certImg))
        }
    }
}