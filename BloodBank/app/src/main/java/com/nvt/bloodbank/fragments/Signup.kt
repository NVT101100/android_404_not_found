package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.databinding.ActivitySignupBinding
import com.nvt.bloodbank.R
import com.nvt.bloodbank.models.SignupModel

class Signup:Fragment() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var model : SignupModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = SignupModel()
        val controller = findNavController()
        binding.txtToSignIn.setOnClickListener {
            controller.navigate(R.id.action_signup_to_signin)
        }
        model.errEmail.observe(viewLifecycleOwner, Observer {
            if(it) Toast.makeText(activity,"Invalid email",Toast.LENGTH_LONG).show()
        })
        model.errPass.observe(viewLifecycleOwner, Observer {
            if(it) Toast.makeText(activity,"Password must be at least 8 character, contain an uppercase letter, a number and a special",Toast.LENGTH_LONG).show()
        })
        model.errRepass.observe(viewLifecycleOwner, Observer {
            if(it) Toast.makeText(activity,"Re-password doesn't match",Toast.LENGTH_LONG).show()
        })
        model.isSuccess.observe(viewLifecycleOwner, Observer {
            val controller = findNavController()
            if(it) {
                Toast.makeText(activity,"Sign up successfully check your verification email",Toast.LENGTH_LONG).show()
                controller.navigate(R.id.action_signup_to_signin)
            } else {
                Toast.makeText(activity,"Failed to sign up with server",Toast.LENGTH_LONG).show()
                binding.passwordSigup.setText("")
                binding.rePasswordSigup.setText("")
            }
        })
        binding.btnSignup.setOnClickListener {
            model.checkSignup(binding.emailSignup.text.toString(),binding.passwordSigup.text.toString(),binding.rePasswordSigup.text.toString())
        }
    }
}