package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.adapters.ContactAdapter
import com.nvt.bloodbank.databinding.ContactBinding
import com.nvt.bloodbank.databinding.EventBinding
import com.nvt.bloodbank.models.ContactModel

class Contact:Fragment() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private lateinit var binding : ContactBinding
    private lateinit var model :ContactModel
    private lateinit var adapter: ContactAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContactBinding.inflate(inflater,container,false)
        model = ContactModel()
        adapter = ContactAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.listHospId.observe(viewLifecycleOwner, Observer {
            model.getListHosp()
        })
        model.listContact.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            val lm = LinearLayoutManager(context)
            binding.listHospitalContact.adapter = adapter
            binding.listHospitalContact.layoutManager = lm
        })
        database.child("contacts").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                model.Init()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}