package com.jve386.superheroapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jve386.superheroapi.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superheroItemResponse: SuperheroItemResponse) {
        binding.tvSuperheroName.text = superheroItemResponse.name
        binding.ivSuperhero

        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)
    }
}