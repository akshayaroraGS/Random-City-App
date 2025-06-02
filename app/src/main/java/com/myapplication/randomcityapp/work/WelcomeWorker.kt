package com.myapplication.randomcityapp.work

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WelcomeWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val cityName = inputData.getString("city_name") ?: return Result.failure()
        
        Toast.makeText(context, "Welcome to $cityName", Toast.LENGTH_LONG).show()
        return Result.success()
    }
} 