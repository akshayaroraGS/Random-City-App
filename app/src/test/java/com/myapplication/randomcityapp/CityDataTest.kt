package com.myapplication.randomcityapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CityDataTest {
    
    @Test
    fun `test CityData data class properties`() {
        val city = "Test City"
        val color = "#FFFFFF"
        val cityData = CityData(city, color)
        
        assertEquals("City property should match", city, cityData.city)
        assertEquals("Color property should match", color, cityData.color)
    }
    
    @Test
    fun `test CityLists contains valid cities`() {
        assertTrue("Cities list should not be empty", CityLists.cities.isNotEmpty())
        assertTrue("Cities list should contain New York", 
            CityLists.cities.contains("New York"))
        assertTrue("Cities list should contain London", 
            CityLists.cities.contains("London"))
    }
    
    @Test
    fun `test CityLists contains valid colors`() {
        assertTrue("Colors list should not be empty", CityLists.colors.isNotEmpty())
        assertTrue("Colors list should contain red", 
            CityLists.colors.contains("#FF0000"))
        assertTrue("Colors list should contain green", 
            CityLists.colors.contains("#00FF00"))
    }
    
    @Test
    fun `test cities and colors lists have same size`() {
        assertEquals("Cities and colors lists should have same size",
            CityLists.cities.size, CityLists.colors.size)
    }
} 