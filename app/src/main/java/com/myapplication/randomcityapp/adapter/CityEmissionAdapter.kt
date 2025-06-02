package com.myapplication.randomcityapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.randomcityapp.R
import com.myapplication.randomcityapp.data.CityEmission
import java.text.SimpleDateFormat
import java.util.Locale

class CityEmissionAdapter(
    private val onItemClick: (CityEmission) -> Unit
) : ListAdapter<CityEmission, CityEmissionAdapter.ViewHolder>(CityEmissionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city_emission, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
        private val emissionTimeTextView: TextView = itemView.findViewById(R.id.emissionTimeTextView)
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        fun bind(emission: CityEmission) {
            cityNameTextView.text = emission.city
            cityNameTextView.setTextColor(Color.parseColor(emission.color))
            emissionTimeTextView.text = dateFormat.format(emission.emissionTime)
            
            itemView.setOnClickListener { onItemClick(emission) }
        }
    }

    private class CityEmissionDiffCallback : DiffUtil.ItemCallback<CityEmission>() {
        override fun areItemsTheSame(oldItem: CityEmission, newItem: CityEmission): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CityEmission, newItem: CityEmission): Boolean {
            return oldItem == newItem
        }
    }
} 