package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.LogState
import com.nvt.bloodbank.databinding.ActivityLoginBinding
import com.nvt.bloodbank.R
import com.nvt.bloodbank.models.LoginModel


class Login:Fragment() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var model : LoginModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = LoginModel()
        binding.textToSignup.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_signin_to_signup)
        }
        model.errEmail.observe(viewLifecycleOwner, Observer {
            if(it == true) Toast.makeText(activity,"Please enter email",Toast.LENGTH_SHORT).show()
        })
        model.errPass.observe(viewLifecycleOwner, Observer {
            if(it == true) Toast.makeText(activity,"Please enter password",Toast.LENGTH_SHORT).show()
        })
        model.logState.observe(viewLifecycleOwner, Observer {
            val controller = findNavController()
            when(it){
                LogState.logged_new ->{
                    controller.navigate(R.id.action_signin_to_addinfo)
                }
                LogState.logged_old -> {
                    controller.navigate(R.id.action_signin_to_home)
                }
                LogState.failed -> {
                    Toast.makeText(activity,"Log in failed",Toast.LENGTH_SHORT).show()
                }
                LogState.unidentified -> {
                    Toast.makeText(activity,"Email unidentified",Toast.LENGTH_SHORT).show()
                    binding.resendEmail.visibility = View.VISIBLE
                }
            }
        })
        binding.btnLogin.setOnClickListener {
            model.doLogin(binding.textEmailLogin.text.toString(),binding.textPasswordLogin.text.toString())
        }
        binding.textVerify.setOnClickListener {
            Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                Toast.makeText(activity,"Email sent",Toast.LENGTH_SHORT)
            }
        }
    }

}