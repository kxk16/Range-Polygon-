package com.example.tmldemoapp3

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mapmyindia.sdk.maps.MapView
import com.mapmyindia.sdk.maps.MapmyIndia
import com.mapmyindia.sdk.maps.MapmyIndiaMap
import com.mapmyindia.sdk.maps.OnMapReadyCallback
import com.mapmyindia.sdk.maps.SupportMapFragment
import com.mapmyindia.sdk.maps.annotations.IconFactory
import com.mapmyindia.sdk.maps.annotations.MarkerOptions
import com.mapmyindia.sdk.maps.annotations.PolygonOptions
import com.mapmyindia.sdk.maps.camera.CameraPosition
import com.mapmyindia.sdk.maps.geometry.LatLng
import com.mmi.services.account.MapmyIndiaAccountManager
import kotlin.math.*


class ThirdActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var mapFragment: SupportMapFragment

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapmyIndiaAccountManager.getInstance().restAPIKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        MapmyIndiaAccountManager.getInstance().mapSDKKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        MapmyIndiaAccountManager.getInstance().atlasClientId = "33OkryzDZsLLtzbaalr2FyI95MRn9G3rN8-CFd0G8r18k35jPBZufxAZIfxaV3TWwryerIlyLcysDQ8AIwfwbQ=="
        MapmyIndiaAccountManager.getInstance().atlasClientSecret = "lrFxI-iSEg-wiV-LqSs7mYtMfVV84kGtAnqMlA0ZQ-wZeH-9OkeIXhpm2I8ZZoq6GX6JF4ghcUyrSBIgPGROY-IB-Veni0qw"
        MapmyIndia.getInstance(applicationContext)

        setContentView(R.layout.activity_third)

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
        var myMap: MapmyIndiaMap = p0

        val latLngList = mutableListOf<LatLng>()

        // Add LatLng objects to the list
        latLngList.add(LatLng(28.549356, 77.26780099999999))
        latLngList.add(LatLng(28.551844, 77.26749))
        latLngList.add(LatLng(28.554454, 77.265473))
        latLngList.add(LatLng(28.549637999999998, 77.262909))

        myMap.addPolygon(
            PolygonOptions()
                .addAll(latLngList)
                .fillColor(Color.parseColor("#3bb2d050")))

        //Calculating the median of all latlngs
        var lat_temp = 0.0
        var lng_temp = 0.0

        for(x in latLngList){
            lat_temp = lat_temp + x.latitude
            lng_temp = lng_temp + x.longitude
        }

        val size = latLngList.size
        lat_temp = lat_temp/size
        lng_temp = lng_temp/size

        for (x in latLngList){
            var y = acos(sin(lat_temp)*sin(x.latitude)+ cos(lat_temp)*cos(x.latitude)*cos(x.longitude -lng_temp))*6371
            Log.d("Dist", "Distance: $y")
        }

        val point: LatLng = LatLng(28.54, 77.27)



//        val markerOptions: MarkerOptions = MarkerOptions().position(latLngList[0]).icon(IconFactory.getInstance(this).fromResource(R.drawable.location_arrow_solid_2))
//        markerOptions.title= "Marker"
//        markerOptions.snippet = "17.036"
//        p0?.addMarker(markerOptions)

//        val markerOptions: MarkerOptions = MarkerOptions().position(point).icon(IconFactory.getInstance(this).fromResource(R.drawable.location_arrow_solid_2))
//        markerOptions.title= "Marker"
//        markerOptions.snippet = "This is a Marker"
//        p0?.addMarker(markerOptions)



        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(lat_temp, lng_temp))
            .zoom(15.0)
            .tilt(0.0)
            .build()
        myMap?.cameraPosition = cameraPosition
    }

    override fun onMapError(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }
}