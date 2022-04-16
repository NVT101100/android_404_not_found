package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nvt.bloodbank.databinding.ContactBinding
import com.nvt.bloodbank.databinding.EventBinding
import com.nvt.bloodbank.models.ContactModel

class Contact:Fragment() {
    private lateinit var binding : ContactBinding
    private lateinit var model :ContactModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContactBinding.inflate(inflater,container,false)
        model = ContactModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.Init()
    }
}