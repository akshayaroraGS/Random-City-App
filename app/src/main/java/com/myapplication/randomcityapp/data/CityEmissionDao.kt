package com.myapplication.randomcityapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityEmissionDao {
    @Query("SELECT * FROM city_emissions ORDER BY city ASC")
    fun getAllEmissions(): Flow<List<CityEmission>>

    @Query("SELECT * FROM city_emissions WHERE id = :id")
    suspend fun getEmissionById(id: Long): CityEmission?

    @Insert
    suspend fun insertEmission(emission: CityEmission): Long
} 