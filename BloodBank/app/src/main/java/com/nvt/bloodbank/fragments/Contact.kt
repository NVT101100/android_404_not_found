package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nvt.bloodbank.databinding.EventBinding

class Contact:Fragment() {
    private lateinit var binding : EventBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EventBinding.inflate(inflater,container,false)
        return binding.root
    }
}