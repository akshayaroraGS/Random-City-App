package com.myapplication.randomcityapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.myapplication.randomcityapp.adapter.CityEmissionAdapter
import com.myapplication.randomcityapp.data.AppDatabase
import com.myapplication.randomcityapp.data.CityEmission
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityEmissionAdapter
    private lateinit var database: AppDatabase
    private var isInForeground = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        database = AppDatabase.getDatabase(this)
        setupRecyclerView()
        setupFab()
        observeEmissions()
    }

    override fun onResume() {
        super.onResume()
        isInForeground = true
    }

    override fun onPause() {
        super.onPause()
        isInForeground = false
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "City Emissions"
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.cityListRecyclerView)
        adapter = CityEmissionAdapter { emission ->
            val intent = Intent(this, CityDetailActivity::class.java).apply {
                putExtra("city_name", emission.city)
                putExtra("color", emission.color)
            }
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupFab() {
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            lifecycleScope.launch {
                CityProducer.createProducer(isInForeground).collect { cityData ->
                    val emission = CityEmission(
                        city = cityData.city,
                        color = cityData.color,
                        emissionTime = Date()
                    )
                    database.cityEmissionDao().insertEmission(emission)
                }
            }
        }
    }

    private fun observeEmissions() {
        lifecycleScope.launch {
            database.cityEmissionDao().getAllEmissions().collectLatest { emissions ->
                adapter.submitList(emissions)
            }
        }
    }
} 