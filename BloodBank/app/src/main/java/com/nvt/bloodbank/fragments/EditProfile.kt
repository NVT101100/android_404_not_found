package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nvt.bloodbank.databinding.EditProfileBinding

class EditProfile:Fragment() {
    private lateinit var binding:EditProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditProfileBinding.inflate(inflater,container,false)
        return binding.root
    }
}