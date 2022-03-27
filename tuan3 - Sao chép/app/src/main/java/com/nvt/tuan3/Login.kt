package com.nvt.tuan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.nvt.tuan3.databinding.LoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding : LoginBinding
    private var myModel : MyModel = MyModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.login)
        val btnLogin = binding.btnLogin

        btnLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onSubmit()
            }
        })
        myModel.errEmail.observe(this, Observer { errEmail ->
            updateView(binding.errEmailLG,myModel.errEmail.value)
        })
        myModel.errPassword.observe(this, Observer { errEmail ->
            updateView(binding.errPassLG,myModel.errPassword.value)
        })
    }

    private fun onSubmit() {
        var email  = binding.edittextuser.text.toString()?.trim()
        var password = binding.edittextpassword.text.toString()?.trim()
        if(myModel.login(email,password)){
            val acProfile:Intent = Intent(this,Profile::class.java)
            acProfile.putExtra("user",User(DataStore.name,DataStore.email,DataStore.phone))
            startActivity(acProfile)
            finish()
        }
        else Toast.makeText(this,"Failed",Toast.LENGTH_SHORT)
    }
    private fun showMessage(message :String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private fun updateView(view:TextView,message:String?) {
        if(message != "") {
            view.text = message
            view.visibility = View.VISIBLE
        }
        else view.visibility = View.GONE
    }
}