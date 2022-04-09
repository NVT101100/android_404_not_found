package com.nvt.tuan5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nvt.tuan5.databinding.SignupBinding

class Signup : Fragment() {
    private lateinit var binding : SignupBinding
    private var signupModel: MyModel=MyModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignup.setOnClickListener { onSubmit() }
        binding.signupEmail.addTextChangedListener { onChange() }
        binding.signupName.addTextChangedListener { onChange() }
        binding.signupPassword.addTextChangedListener{onChange()}
        signupModel.errEmail.observe(viewLifecycleOwner,Observer {errEmail ->
            updateErr(binding.errEmail,signupModel.errEmail.value)
        })
        signupModel.errName.observe(viewLifecycleOwner, Observer { errName ->
            updateErr(binding.errName,signupModel.errName.value);
        })
        signupModel.errPassword.observe(viewLifecycleOwner, Observer { errPass ->
            updateErr(binding.errPassword,signupModel.errPassword.value);
        })
    }

    private fun onChange(){
        val email = binding.signupEmail.text.toString()?.trim()
        val password = binding.signupPassword.text.toString()?.trim()
        val name = binding.signupName.text.toString()?.trim()
        signupModel.checkSignup(email, password, name)
    }

    private fun onSubmit(){
        val controller = findNavController()
        if(signupModel.errEmail.value == "" && signupModel.errName.value == "" && signupModel.errPassword.value == "") {
            DataStore.email = binding.signupEmail.text.toString().trim()
            DataStore.name = binding.signupName.text.toString().trim()
            DataStore.password = binding.signupPassword.text.toString().trim()
            print(DataStore.password)
            controller.navigate(R.id.signup_to_login)
        }
        else Toast.makeText(activity,"Failed",Toast.LENGTH_SHORT)
    }

    private fun updateErr(view : TextView,message:String?){
        if(message != "") {
            view.text = message
            view.visibility = View.VISIBLE
        }
        else view.visibility = View.GONE
    }
}