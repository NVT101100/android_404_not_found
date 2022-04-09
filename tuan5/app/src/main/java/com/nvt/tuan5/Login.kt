package com.nvt.tuan5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nvt.tuan5.databinding.LoginBinding

class Login : Fragment() {
    private lateinit var sharedPref : SharedPreferences
    private lateinit var binding : LoginBinding
    private var myModel : MyModel = MyModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin = binding.btnLogin
        btnLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onSubmit()
            }
        })
        myModel.errEmail.observe(viewLifecycleOwner, Observer { errEmail ->
            updateView(binding.errEmailLG,myModel.errEmail.value)
        })
        myModel.errPassword.observe(viewLifecycleOwner, Observer { errEmail ->
            updateView(binding.errPassLG,myModel.errPassword.value)
        })
    }

    private fun onSubmit() {
        var email  = binding.edittextuser.text.toString()?.trim()
        var password = binding.edittextpassword.text.toString()?.trim()
        if(myModel.login(email,password)){
            sharedPref = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            var user = sharedPref.getString("user",null)
            var password_shared =  sharedPref.getString("password",null)
            if(user == null && password == null){
                editor.putString("user",email)
                editor.putString("password",password_shared)
                editor.commit()
            }
            val controller = findNavController()
            controller.navigate(R.id.login_to_home)
        }
        else Toast.makeText(activity,"Failed",Toast.LENGTH_SHORT)
    }
    private fun showMessage(message :String){
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
    }
    private fun updateView(view:TextView,message:String?) {
        if(message != "") {
            view.text = message
            view.visibility = View.VISIBLE
        }
        else view.visibility = View.GONE
    }
}