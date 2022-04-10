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
import com.nvt.bloodbank.databinding.ActivitySearchBinding

class SearchFragment:Fragment() {
    private var database = Firebase.database(Constants.databaseURL).reference
    private lateinit var binding : ActivitySearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySearchBinding.inflate(inflater,container,false)
        return binding.root
    }

}
