package com.nvt.tuan4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nvt.tuan4.databinding.ProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var binding : ProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        binding = DataBindingUtil.setContentView(this, R.layout.profile)
        binding.user = intent.getParcelableExtra("user")
    }
}