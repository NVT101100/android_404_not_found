package com.nvt.tuan5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nvt.tuan5.databinding.WelcomeBinding

class OnBoard1:Fragment() {
    private lateinit var sharedPref : SharedPreferences
    private var isNew : Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.onboard1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        isNew = sharedPref.getBoolean("first-time",isNew)
        val controller = findNavController()
        if(isNew) {
            val editor = sharedPref.edit()
            editor.putBoolean("first-time",false)
            editor.commit()
            val handler = Handler(Looper.getMainLooper())
            val runnable:Runnable = object:Runnable {
                override fun run() {
                    controller.navigate(R.id.newUser)
                }
            }
            handler.postDelayed(runnable,4000)
        }
        else controller.navigate(R.id.board1_to_welcome)
    }
}