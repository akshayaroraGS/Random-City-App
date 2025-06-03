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
        return CityLists.cityCoordinates[city] ?: LatLng(0.0, 0.0)
    }
} 