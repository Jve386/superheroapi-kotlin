package com.jve386.superheroapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperheroAdapter(
    var superheroList: List<SuperheroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperheroViewHolder>() {

    // Function to update the superheroList and notify the adapter
    fun updateList(superheroList: List<SuperheroItemResponse>) {
        this.superheroList = superheroList
        notifyDataSetChanged()
    }

    // Create a new SuperheroViewHolder when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    // Return the total number of items in the dataset
    override fun getItemCount(): Int = superheroList.size

    // Bind data to the ViewHolder at the given position
    override fun onBindViewHolder(vholder: SuperheroViewHolder, position: Int) {
        vholder.bind(superheroList[position], onItemSelected)
    }

}
