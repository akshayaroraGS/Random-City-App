package com.myapplication.randomcityapp

data class CityData(
    val city: String,
    val color: String
)

object CityLists {
    val cities = listOf(
        "New York",
        "London",
        "Paris",
        "Tokyo",
        "Sydney",
        "Berlin",
        "Rome",
        "Moscow",
        "Dubai",
        "Singapore"
    )

    val colors = listOf(
        "#FF0000", // Red
        "#00FF00", // Green
        "#0000FF", // Blue
        "#FFFF00", // Yellow
        "#FF00FF", // Magenta
        "#00FFFF", // Cyan
        "#FFA500", // Orange
        "#800080", // Purple
        "#008000", // Dark Green
        "#800000"  // Maroon
    )
} 