package com.myapplication.randomcityapp

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

object CityProducer {
    private val random = Random(System.currentTimeMillis())

    val producer: Flow<CityData> = flow {
        while (true) {
            val randomCity = CityLists.cities.random(random)
            val randomColor = CityLists.colors.random(random)
            emit(CityData(randomCity, randomColor))
            delay(5000) // 5 seconds delay
        }
    }
} 