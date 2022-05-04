package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R
import com.nvt.bloodbank.databinding.EditProfileBinding
import com.nvt.bloodbank.dto.Users

class EditProfile:Fragment() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private val auth = Firebase.auth.currentUser
    private lateinit var binding:EditProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database.child("users/${auth!!.uid}").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue<Users>()
                val splitDate = user!!.dob?.split("/")
                binding.info = user
                binding.dob.init(splitDate?.get(2)!!.toInt(),splitDate[0].toInt(),splitDate[1].toInt(),null)
                when (user.gender) {
                    "male" -> { binding.genderGroup.check(R.id.male)}
                    "female" -> {binding.genderGroup.check(R.id.female)}
                    else -> {binding.genderGroup.check(R.id.other)}
                }
                if(user.isDonor) binding.state.check(R.id.Donor)
                else binding.state.check(R.id.Receiver)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}