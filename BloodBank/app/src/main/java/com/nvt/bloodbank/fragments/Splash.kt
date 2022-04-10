package com.nvt.bloodbank.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nvt.bloodbank.Constants
import com.nvt.bloodbank.LogState
import com.nvt.bloodbank.R
import com.nvt.bloodbank.dto.Blood
import com.nvt.bloodbank.dto.Events
import com.nvt.bloodbank.dto.Hospitals
import com.nvt.bloodbank.models.LoginModel


class Splash:Fragment() {
    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var handler: Handler
    private lateinit var model : LoginModel
    private var database = Firebase.database(Constants.databaseURL).reference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = LoginModel()
        val imageView: ImageView = view.findViewById(R.id.load_image)
        imageView.apply {
            setBackgroundResource(R.drawable.spalsh_animation);
            animationDrawable = background as AnimationDrawable
        }
        animationDrawable.start()
        handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object :Runnable {
            override fun run() {
                model.checkState()
            }
        }
        handler.postDelayed(runnable,3000);
        model.logState.observe(viewLifecycleOwner, Observer {
            val controller = findNavController()
            when(it) {
                LogState.logged_old -> {
                    controller.navigate(R.id.splash_to_home)
                }
                LogState.logged_new -> {
                    controller.navigate(R.id.action_splash_to_addinfo)
                }
                else -> {
                    controller.navigate(R.id.action_splash_to_signin)
                }
            }
        })
    }


}