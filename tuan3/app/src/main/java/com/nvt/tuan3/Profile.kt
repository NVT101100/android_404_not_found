package com.nvt.tuan3

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputLayout
import com.nvt.tuan3.databinding.ProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var binding : ProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        binding = DataBindingUtil.setContentView(this, R.layout.profile)
        binding.user = intent.getParcelableExtra("user")
    }
}