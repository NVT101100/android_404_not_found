package com.nvt.tuan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nvt.tuan3.databinding.SignupBinding

class Signup : AppCompatActivity() {
    private lateinit var binding : SignupBinding
    private var signupModel: MyModel = MyModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.signup)
        binding.btnSignup.setOnClickListener { onSubmit() }
        binding.signupEmail.addTextChangedListener { onChange() }
        binding.signupName.addTextChangedListener { onChange() }
        binding.signupPassword.addTextChangedListener{onChange()}
        signupModel.errEmail.observe(this,Observer {errEmail ->
            updateErr(binding.errEmail,signupModel.errEmail.value)
        })
        signupModel.errName.observe(this, Observer { errName ->
            updateErr(binding.errName,signupModel.errName.value);
        })
        signupModel.errPassword.observe(this, Observer { errPass ->
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
        if(signupModel.errEmail.value == "" && signupModel.errName.value == "" && signupModel.errPassword.value == "") {
            DataStore.email = binding.signupEmail.text.toString().trim()
            DataStore.name = binding.signupName.text.toString().trim()
            DataStore.password = binding.signupPassword.text.toString().trim()
            print(DataStore.password)
            startActivity(Intent(this,Login::class.java))
            finish()
        }
        else Toast.makeText(this,"Failed",Toast.LENGTH_SHORT)
    }

    private fun updateErr(view : TextView,message:String?){
        if(message != "") {
            view.text = message
            view.visibility = View.VISIBLE
        }
        else view.visibility = View.GONE
    }
}