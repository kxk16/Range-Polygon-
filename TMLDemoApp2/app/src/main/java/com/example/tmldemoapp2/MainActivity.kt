package com.example.tmldemoapp2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mapmyindia.sdk.maps.MapFragment
import com.mapmyindia.sdk.maps.MapView
import com.mapmyindia.sdk.maps.MapmyIndia
import com.mapmyindia.sdk.maps.MapmyIndiaMap
import com.mapmyindia.sdk.maps.OnMapReadyCallback
import com.mapmyindia.sdk.maps.SupportMapFragment
import com.mapmyindia.sdk.maps.annotations.PolygonOptions
import com.mapmyindia.sdk.maps.camera.CameraPosition
import com.mapmyindia.sdk.maps.camera.CameraUpdate
import com.mapmyindia.sdk.maps.camera.CameraUpdateFactory
import com.mapmyindia.sdk.maps.geometry.LatLng
import com.mmi.services.account.MapmyIndiaAccountManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var mapFragment: SupportMapFragment
    private var latLngList = mutableListOf<LatLng>()

    val BASE_URL = "http://10.0.2.2:3000/"

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
        var myMap: MapmyIndiaMap = p0


//        p0?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(18.650767,73.812482), 14.0))
//        p0?.easeCamera(CameraUpdateFactory.newLatLngZoom(LatLng(18.650767,73.812482), 14.0))
//        p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(18.650767,73.812482), 14.0))


        getMyData()


        // Add LatLng objects to the list
//        latLngList.add(LatLng(28.549356, 77.26780099999999))
//        latLngList.add(LatLng(28.551844, 77.26749))
//        latLngList.add(LatLng(28.554454, 77.265473))
//        latLngList.add(LatLng(28.549637999999998, 77.262909))

        myMap.addPolygon(
            PolygonOptions()
            .addAll(latLngList)
            .fillColor(Color.parseColor("#3bb2d0")))

        //Calculating the median of all latlngs
        var lat_temp = 0.0
        var lng_temp = 0.0

        for (x in latLngList) {
            lat_temp += x.latitude
            lng_temp += x.longitude
        }

        val size = latLngList.size
        if (size > 0) {
            lat_temp /= size
            lng_temp /= size
        }


        val cameraPosition = CameraPosition.Builder()
//            .target(LatLng(18.650767, 73.812482))
            .target(LatLng(lat_temp, lng_temp))
            .zoom(15.0)
            .tilt(0.0)
            .build()
        myMap?.cameraPosition = cameraPosition

///////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<Geopoint>?> {
            override fun onResponse(
                call: Call<List<Geopoint>?>,
                response: Response<List<Geopoint>?>
            ) {
                val responseBody = response.body()!!

                for (myData in responseBody) {
                    val lat = myData.lat.toDouble()
                    val lng = myData.lng.toDouble()

                    if (lat != null && lng != null && !lat.isNaN() && !lng.isNaN()) {
                        latLngList.add(LatLng(lat, lng))
                    } else {
                        // Handle invalid latitude or longitude
                        // Log a warning, skip this data point, or provide default values
                        Log.w("LatLng", "Invalid latitude or longitude for data: $myData")
                    }
                }


            }

            override fun onFailure(call: Call<List<Geopoint>?>, t: Throwable) {
            }
        })
    }

    override fun onMapError(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }
}

