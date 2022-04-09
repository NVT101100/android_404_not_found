package com.nvt.tuan5

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        val view = findViewById<FragmentContainerView>(R.id.host_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_nav) as NavHostFragment
        val navController = navHostFragment.navController
        val viewParam = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT)
        navigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.navigationHome -> {
                    navigation?.visibility = View.VISIBLE
                    viewParam.weight = 1.0f
                    view.layoutParams = viewParam
                }
                R.id.navigationProfile ->{
                    navigation?.visibility = View.VISIBLE
                    viewParam.weight = 1.0f
                    view.layoutParams = viewParam
                }
                else -> {
                    navigation?.visibility = View.INVISIBLE
                    viewParam.weight = 0.0f
                    view.layoutParams = viewParam
                }
            }
        }
    }
}