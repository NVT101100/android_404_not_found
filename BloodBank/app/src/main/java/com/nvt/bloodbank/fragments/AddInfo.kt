package com.nvt.bloodbank.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.R
import com.nvt.bloodbank.bldGrp
import com.nvt.bloodbank.databinding.ActivityAddInfoBinding
import com.nvt.bloodbank.dto.Users
import com.nvt.bloodbank.models.InfoModel

class AddInfo:Fragment() {
    private lateinit var binding: ActivityAddInfoBinding
    private lateinit var model : InfoModel
    private var database = Firebase.database(Constants.databaseURL).reference
    private var auth = Firebase.auth.currentUser
    private val IMAGE_REQUEST_CODE = 1_000
    private val storage = Firebase.storage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityAddInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = InfoModel()
        binding.spinner.setEndIconOnClickListener {v:View ->
            showMenu(v,R.menu.pop_up_menu)
        }
        var name = ""
        var address = ""
        var dob = ""
        var phone = ""
        var gender = ""
        var blood = bldGrp.ANeg
        var id = ""
        var isr = ""
        var state :Boolean? = null
        var profile = ""
        binding.submitProfile.setOnClickListener {
            name = binding.name.text.toString()
            address = binding.address.text.toString()
            dob = binding.dob.dayOfMonth.toString()+"/"+(binding.dob.month+1).toString()+"/"+binding.dob.year.toString()
            phone = binding.phoneNumber.text.toString()
            gender = ""
            when(binding.genderGroup.checkedRadioButtonId){
                R.id.male->gender="male"
                R.id.female->gender="female"
                R.id.other->gender="other"
            }
            blood = bldGrp.valueOf(binding.optionView.text.toString())
            id = binding.idNumber.text.toString()
            isr = binding.insuranceNumber.text.toString()
            when(binding.state.checkedRadioButtonId) {
                R.id.Donor->state = true
                R.id.Receiver->state = false
            }
            storage.reference.child("profile/"+auth!!.uid).downloadUrl.addOnFailureListener {
                profile = ""
                model.checkInput(name,address,dob,phone,gender,state,id,isr)
            }.addOnSuccessListener {
                profile = it.toString()
                model.checkInput(name,address,dob,phone,gender,state,id,isr)
            }
        }
        model.errName.observe(viewLifecycleOwner, Observer {
            if(it) binding.errName.visibility = View.VISIBLE
            else if(!it) binding.errName.visibility = View.GONE
        })
        model.errAddress.observe(viewLifecycleOwner, Observer {
            if(it) binding.errAdress.visibility = View.VISIBLE
            else if(!it) binding.errAdress.visibility = View.GONE
        })
        model.errPhone.observe(viewLifecycleOwner, Observer {
            if(it) binding.errPhone.visibility = View.VISIBLE
            else if(!it) binding.errPhone.visibility = View.GONE
        })
        model.errGender.observe(viewLifecycleOwner, Observer {
            if(it) binding.errGender.visibility = View.VISIBLE
            else if(!it) binding.errGender.visibility = View.GONE
        })
        model.errAge.observe(viewLifecycleOwner, Observer {
            if(it) binding.errAge.visibility = View.VISIBLE
            else if(!it) binding.errAge.visibility = View.GONE
        })
        model.errState.observe(viewLifecycleOwner, Observer {
            if(it) binding.errState.visibility = View.VISIBLE
            else if(!it) binding.errState.visibility = View.GONE
        })
        model.errIdNum.observe(viewLifecycleOwner, Observer {
            if(it) binding.errId.visibility = View.VISIBLE
            else if(!it) binding.errId.visibility = View.GONE
        })
        model.errIsrNum.observe(viewLifecycleOwner, Observer {
            if(it) binding.errIsr.visibility = View.VISIBLE
            else if(!it) binding.errIsr.visibility = View.GONE
        })
        model.isComplete.observe(viewLifecycleOwner, Observer {
            if(it) database.child("users").child(auth!!.uid)
                .setValue(
                    Users(profile,name, address, dob, phone, gender, blood,id,isr, state==true)).addOnSuccessListener {
                    val controller = findNavController()
                    controller.navigate(R.id.action_addinfo_to_home)
                }.addOnFailureListener {
                    Toast.makeText(context,"Failed to sign up to database",Toast.LENGTH_SHORT).show()
                }
        })
        binding.btnAddImg.setOnClickListener {
            pickImageFromGallery()
        }
    }

    override fun onStop() {
        super.onStop()
    }
    private fun showMenu(v: View,@MenuRes menuRes: Int) {
        val popup = PopupMenu(context,v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem : MenuItem ->
            itemSelect(menuItem)
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }
    private fun itemSelect(menuItem: MenuItem):Boolean{
        binding.optionView.setText(menuItem.title)
        return true
    }
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            val storageRef = storage.reference
            var file = data?.data
            val profileRef = storageRef.child("profile/"+auth!!.uid)
            if (file != null) {
                profileRef.putFile(file).addOnSuccessListener{
                    binding.profileImg.setImageURI(file)
                }
            }
        }
    }

}