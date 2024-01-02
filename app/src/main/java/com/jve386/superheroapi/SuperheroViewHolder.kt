package com.jve386.superheroapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jve386.superheroapi.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

// ViewHolder class for displaying superhero items in a RecyclerView
class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // View Binding for the superhero item layout
    private val binding = ItemSuperheroBinding.bind(view)

    // Function to bind data to the ViewHolder
    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected: (String) -> Unit) {
        // Set the superhero's name to the corresponding TextView
        binding.tvSuperheroName.text = superheroItemResponse.name

        // Load the superhero's image using Picasso into the ImageView
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)

        // Set a click listener for the whole item view to handle item selection
        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) }
    }
}
