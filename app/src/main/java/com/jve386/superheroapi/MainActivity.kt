package com.jve386.superheroapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jve386.superheroapi.DetailSuperheroActivity.Companion.EXTRA_ID
import com.jve386.superheroapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use View Binding to inflate the layout and initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the content view of the activity to the root view of the inflated layout
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        // Set up the SearchView's query listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Trigger a search when the user submits the query
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        // Initialize the RecyclerView and its adapter
        adapter = SuperheroAdapter { navigateToDetail(it) }
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter
    }

    private fun searchByName(query: String) {
        // Show progress bar while fetching data
        binding.progerssBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            // Make a network request to get superheroes by name
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperheroes(query)

            if (myResponse.isSuccessful) {
                val data = myResponse.body()
                if (data != null) {
                    // Log success and update the UI on the main thread
                    Log.i("jveApp", "It works")
                    runOnUiThread {
                        adapter.updateList(data.superheroes)
                        binding.progerssBar.isVisible = false
                    }
                } else {
                    // Log if the data is null
                    Log.i("jveApp", "data is null")
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        // Function to create a Retrofit instance
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        // Function to navigate to the DetailSuperheroActivity with the selected superhero ID
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
