package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nvt.bloodbank.R


class MapFragment:Fragment(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.activity_maps,container,false)
        val mapView = view.findViewById<MapView>(R.id.ggmap)
        mapView.getMapAsync(this)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        return view
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}