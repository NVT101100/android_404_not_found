package com.nvt.bloodbank.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.nvt.bloodbank.R
import com.nvt.bloodbank.fragments.HomeFragment
import com.nvt.bloodbank.fragments.MapFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        val view = findViewById<FragmentContainerView>(R.id.nav_host)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.home -> {
                    bottomNavActive(navigation,view,true)
                }
                R.id.map ->{
                    bottomNavActive(navigation,view,true)
                }
                R.id.search -> {
                    bottomNavActive(navigation,view,true)
                    controller.popBackStack(R.id.search,false)
                }
                else -> {
                    bottomNavActive(navigation,view,false)
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