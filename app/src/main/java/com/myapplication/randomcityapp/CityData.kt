package com.myapplication.randomcityapp

data class CityData(
    val city: String,
    val color: String
)

object CityLists {
    val cities = listOf("New York", "Los Angeles", "Scranton", "Philadelphia", "Nashville", "Saint Louis", "Miami")

    val colors = listOf(
        "#FFFF00", // Yellow
        "#00FF00", // Green
        "#0000FF", // Blue
        "#FF0000", // Red
        "#FF00FF", // Magenta
        "#00FFFF", // Cyan
        "#FFA500", // Orange
    )

    val cityCoordinates = mapOf(
        "New York" to com.google.android.gms.maps.model.LatLng(40.7128, -74.0060),
        "Los Angeles" to com.google.android.gms.maps.model.LatLng(34.0522, -118.2437),
        "Scranton" to com.google.android.gms.maps.model.LatLng(41.4089, -75.6624),
        "Philadelphia" to com.google.android.gms.maps.model.LatLng(39.9526, -75.1652),
        "Nashville" to com.google.android.gms.maps.model.LatLng(36.1627, -86.7816),
        "Saint Louis" to com.google.android.gms.maps.model.LatLng(38.6270, -90.1994),
        "Miami" to com.google.android.gms.maps.model.LatLng(25.7617, -80.1918)
    )
} 