package com.nvt.bloodbank.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nvt.bloodbank.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        val view = findViewById<FragmentContainerView>(R.id.nav_host)
        val navController = findNavController(R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,R.id.search,R.id.map,R.id.profile,R.id.contact
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.splash -> {
                    bottomNavActive(navigation, view, false)
                }
                R.id.signin -> {
                    bottomNavActive(navigation, view, false)
                }
                R.id.signup -> {
                    bottomNavActive(navigation, view, false)
                }
                R.id.addinfo -> {
                    bottomNavActive(navigation, view, false)
                }
                else -> {
                    bottomNavActive(navigation, view, true)
                }
            }
        }
    }
    private fun bottomNavActive(navigationView: BottomNavigationView,view:View,ON:Boolean){
        val viewParam = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT)
        if(ON) {
            navigationView?.visibility = View.VISIBLE
            viewParam.weight = 1.0f
            view.layoutParams = viewParam
        }
        else {
            navigationView.visibility = View.INVISIBLE
            viewParam.weight = 0.0f
            view.layoutParams = viewParam
        }
    }

}