package com.nvt.bloodbank.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
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
import com.nvt.bloodbank.fragments.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mMap: GoogleMap
    private var Auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation)
        navigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> {
                    val fragment = HomeFragment()
                    showFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_map -> {
                    val fragment = MapFragment()
                    showFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_search -> {
                    val fragment = SearchFragment()
                    showFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        //Auth.signOut()
    }

    private fun showFragment(fragment: Fragment){
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.fragment_container_view,fragment)
        fram.commit()
    }

}