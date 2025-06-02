package com.myapplication.randomcityapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.myapplication.randomcityapp.work.WelcomeWorker

class CityDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        cityName = intent.getStringExtra("city_name") ?: return
        val color = intent.getStringExtra("color") ?: return

        setupToolbar(color)
        setupMap()
        scheduleWelcomeToast()
    }

    private fun setupToolbar(color: String) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = cityName
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        toolbar.setBackgroundColor(Color.parseColor(color))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        // Center the map on the city (you'll need to implement city coordinates)
        val cityLocation = getCityLocation(cityName)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocation, 12f))
    }

    private fun scheduleWelcomeToast() {
        val welcomeWorkRequest = OneTimeWorkRequestBuilder<WelcomeWorker>()
            .setInputData(workDataOf("city_name" to cityName))
            .build()

        WorkManager.getInstance(this).enqueue(welcomeWorkRequest)
    }

    private fun getCityLocation(city: String): LatLng {
        // This is a simplified version. You should implement proper city coordinates
        return when (city) {
            "New York" -> LatLng(40.7128, -74.0060)
            "London" -> LatLng(51.5074, -0.1278)
            "Paris" -> LatLng(48.8566, 2.3522)
            "Tokyo" -> LatLng(35.6762, 139.6503)
            "Sydney" -> LatLng(-33.8688, 151.2093)
            "Berlin" -> LatLng(52.5200, 13.4050)
            "Rome" -> LatLng(41.9028, 12.4964)
            "Moscow" -> LatLng(55.7558, 37.6173)
            "Dubai" -> LatLng(25.2048, 55.2708)
            "Singapore" -> LatLng(1.3521, 103.8198)
            else -> LatLng(0.0, 0.0)
        }
    }
} 