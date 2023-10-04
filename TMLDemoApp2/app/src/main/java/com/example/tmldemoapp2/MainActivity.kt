package com.example.tmldemoapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mapmyindia.sdk.maps.MapFragment
import com.mapmyindia.sdk.maps.MapView
import com.mapmyindia.sdk.maps.MapmyIndia
import com.mapmyindia.sdk.maps.MapmyIndiaMap
import com.mapmyindia.sdk.maps.OnMapReadyCallback
import com.mapmyindia.sdk.maps.SupportMapFragment
import com.mapmyindia.sdk.maps.camera.CameraPosition
import com.mapmyindia.sdk.maps.camera.CameraUpdate
import com.mapmyindia.sdk.maps.geometry.LatLng
import com.mmi.services.account.MapmyIndiaAccountManager

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        MapmyIndiaAccountManager.getInstance().restAPIKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        MapmyIndiaAccountManager.getInstance().mapSDKKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        MapmyIndiaAccountManager.getInstance().atlasClientId = "33OkryzDZsLLtzbaalr2FyI95MRn9G3rN8-CFd0G8r18k35jPBZufxAZIfxaV3TWwryerIlyLcysDQ8AIwfwbQ=="
        MapmyIndiaAccountManager.getInstance().atlasClientSecret = "lrFxI-iSEg-wiV-LqSs7mYtMfVV84kGtAnqMlA0ZQ-wZeH-9OkeIXhpm2I8ZZoq6GX6JF4ghcUyrSBIgPGROY-IB-Veni0qw"
        MapmyIndia.getInstance(applicationContext)

        setContentView(R.layout.activity_main)


        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
        
        mapFragment = supportFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this, )

    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onMapReady(p0: MapmyIndiaMap) {
        var myMap: MapmyIndiaMap

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(18.650767, 73.812482))
            .zoom(10.0)
            .tilt(0.0)
            .build()
        p0?.cameraPosition = cameraPosition

    }

    override fun onMapError(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }
}

