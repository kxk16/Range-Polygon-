package com.example.tmldemoapp


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mmi.LicenceManager
import com.mmi.MapmyIndiaMapView
import com.mmi.layers.Polygon
import com.mmi.util.GeoPoint
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // on below line we are setting rest api key and map sdk key.
        LicenceManager.getInstance().restAPIKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        LicenceManager.getInstance().mapSDKKey = "ab5c9155a74d2b5b8576368f8ed6a321"
        // on below line we are creating variables for map view and initializing it.
        val mapmyIndiaMapView = findViewById<MapmyIndiaMapView>(R.id.idMapView)
        // on below line we are getting our map view.
        val mapView = mapmyIndiaMapView.mapView
        // on below line we are creating an
        // array list to display all markers.
        val geoPoints = ArrayList<GeoPoint>()
        // on below line adding marker geo point to our list.
        geoPoints.add(GeoPoint(28.549356, 77.26780099999999))
        geoPoints.add(GeoPoint(28.551844, 77.26749))
        geoPoints.add(GeoPoint(28.554454, 77.265473))
        geoPoints.add(GeoPoint(28.549637999999998, 77.262909))
        // on below line we are creating polygon.
        val polygon: Polygon = Polygon(this)
        // on below line setting points for poly gon.
        polygon.points = geoPoints
        // on below line adding a fill color for poly gon
        polygon.fillColor = Color.GREEN
        // on below line setting map view bounds.
        mapView.setBounds(geoPoints)
        // on below line adding polygon.
        mapView.getOverlays().add(polygon)
        // on below line calling map view.
        mapView.invalidate()
    }
}
