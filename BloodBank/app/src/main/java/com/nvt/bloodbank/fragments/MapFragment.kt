package com.nvt.bloodbank.fragments

import android.Manifest
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nvt.bloodbank.R


class MapFragment:Fragment(),OnMapReadyCallback,LocationListener {
    private lateinit var mMap: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val args : MapFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.activity_maps,container,false)
        val mapView = view.findViewById<MapView>(R.id.ggmap)
        mapView.getMapAsync(this)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        val locationPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
                }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity().baseContext)
        return view
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap.isMyLocationEnabled = true
        val eventDetails = args.eventPosition
        if(eventDetails != null) {
            val mypos = LatLng(eventDetails.eventPosition.latitude, eventDetails.eventPosition.longitude)
            mMap.addMarker(MarkerOptions().position(mypos))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mypos))
        }
    }

    override fun onResume() {
        super.onResume()
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener {
            if (it != null) {
                currentLocation = it
            }
        }
    }

    override fun onLocationChanged(p0: Location) {
        val eventDetails = args.eventPosition
        val mypos = LatLng(currentLocation.latitude,currentLocation.longitude)
        mMap.addMarker(MarkerOptions().position(mypos))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mypos))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}