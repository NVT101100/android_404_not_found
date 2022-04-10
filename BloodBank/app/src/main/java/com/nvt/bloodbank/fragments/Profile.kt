package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nvt.bloodbank.databinding.ProfileBinding

class Profile:Fragment() {
    private lateinit var binding : ProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileBinding.inflate(inflater,container,false)
        return binding.root
    }
}