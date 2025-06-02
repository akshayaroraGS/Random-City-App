package com.myapplication.randomcityapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "city_emissions")
data class CityEmission(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val city: String,
    val color: String,
    val emissionTime: Date
) 