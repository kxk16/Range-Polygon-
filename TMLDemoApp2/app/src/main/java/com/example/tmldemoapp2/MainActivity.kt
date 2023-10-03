package com.example.tmldemoapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mapmyindia.sdk.maps.MapView
import com.mapmyindia.sdk.maps.MapmyIndia
import com.mmi.services.account.MapmyIndiaAccountManager

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        LicenceManager.getInstance().setRestAPIKey("your_rest_api_key")
//        LicenceManager.getInstance().setMapSDKKey("your_java_script_key")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MapmyIndiaAccountManager.getInstance().restAPIKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        MapmyIndiaAccountManager.getInstance().mapSDKKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        MapmyIndiaAccountManager.getInstance().atlasClientId = "33OkryzDZsLLtzbaalr2FyI95MRn9G3rN8-CFd0G8r18k35jPBZufxAZIfxaV3TWwryerIlyLcysDQ8AIwfwbQ=="
        MapmyIndiaAccountManager.getInstance().atlasClientSecret = "lrFxI-iSEg-wiV-LqSs7mYtMfVV84kGtAnqMlA0ZQ-wZeH-9OkeIXhpm2I8ZZoq6GX6JF4ghcUyrSBIgPGROY-IB-Veni0qw"
        MapmyIndia.getInstance(applicationContext)

        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
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
}