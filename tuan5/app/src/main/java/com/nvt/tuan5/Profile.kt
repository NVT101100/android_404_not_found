package com.nvt.tuan5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nvt.tuan5.databinding.ProfileBinding

class Profile : Fragment() {
    private lateinit var binding : ProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileBinding.inflate(inflater,container,false)
        binding.user = User(DataStore.name,DataStore.email,DataStore.phone)
        binding.btnsignout.setOnClickListener { 
            val controller = findNavController()
            controller.navigate(R.id.signout)
        }
        return binding.root
    }
}