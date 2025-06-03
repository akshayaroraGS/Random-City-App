package com.myapplication.randomcityapp

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CityProducerTest {
    
    @Test
    fun `test city producer generates valid city`() = runBlocking {
        val cityData = CityProducer.createProducer(true).first()
        
        assertTrue("City should be in the predefined list", 
            CityLists.cities.contains(cityData.city))
    }
    
    @Test
    fun `test city producer generates valid color`() = runBlocking {
        val cityData = CityProducer.createProducer(true).first()
        
        assertTrue("Color should be in the predefined list", 
            CityLists.colors.contains(cityData.color))
    }
    
    @Test
    fun `test city producer generates different cities`() = runBlocking {
        val firstCity = CityProducer.createProducer(true).first()
        val secondCity = CityProducer.createProducer(true).first()
        
        // Note: This test might occasionally fail due to random nature
        // In a real app, we would mock the Random instance
        assertTrue("Cities should be different", 
            firstCity.city != secondCity.city || firstCity.color != secondCity.color)
    }
} 