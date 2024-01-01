package com.jve386.superheroapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.notify

class SuperheroAdapter(
    var superheroList: List<SuperheroItemResponse> = emptyList()
) :
    RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(superheroList: List<SuperheroItemResponse>) {
        this.superheroList = superheroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun getItemCount(): Int = superheroList.size

    override fun onBindViewHolder(vholder: SuperheroViewHolder, position: Int) {
        vholder.bind(superheroList[position])
    }

}
