package com.nvt.tuan5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nvt.tuan5.databinding.WelcomeBinding

class Welcome : Fragment() {
    private lateinit var  binding : WelcomeBinding
    private lateinit var sharedPref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WelcomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val skip:Button = binding.btnSkip
        val signin:TextView = binding.txtSignin
        val controller = findNavController()
        skip.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                controller.navigate(R.id.welcome_to_signup)
            }
        })
        signin.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                controller.navigate(R.id.welcome_to_login)
            }
        })
        sharedPref = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        if (sharedPref.getString("user",null) != null && sharedPref.getString("password",null)!=null) {
            val controller = findNavController()
            controller.navigate(R.id.welcome_to_home)
        }

    }
}