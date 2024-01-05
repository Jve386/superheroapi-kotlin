package com.jve386.superheroapi.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jve386.superheroapi.adapter.SuperheroAdapter
import com.jve386.superheroapi.contract.SuperheroContract
import com.jve386.superheroapi.data.SuperHeroDataResponse
import com.jve386.superheroapi.data.SuperHeroDetailResponse
import com.jve386.superheroapi.data.SuperheroItemResponse
import com.jve386.superheroapi.presenter.SuperheroPresenter
import com.jve386.superheroapi.model.SuperheroRepository
import com.jve386.superheroapi.network.ApiService
import com.jve386.superheroapi.ui.DetailSuperheroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.lifecycleScope
import com.jve386.superheroapi.databinding.ActivityMainBinding
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity(), SuperheroContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperheroAdapter
    private lateinit var presenter: SuperheroContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use View Binding to inflate the layout and initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the content view of the activity to the root view of the inflated layout
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
        initPresenter()
    }

    private fun initUI() {
        // Set up the SearchView's query listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Trigger a search when the user submits the query
                lifecycleScope.launch {
                    presenter.searchSuperheroes(query.orEmpty())
                }
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




    private fun initPresenter() {
        val apiService = retrofit.create(ApiService::class.java)
        val repository = SuperheroRepository(apiService)
        presenter = SuperheroPresenter(repository)
        presenter.attachView(this)
    }

    private suspend fun searchByName(query: String) {
        // Show progress bar while fetching data
        withContext(Dispatchers.Main) {
            binding.progerssBar.isVisible = true
        }

        // Make a network request to get superheroes by name
        val myResponse: Response<SuperHeroDataResponse> =
            retrofit.create(ApiService::class.java).getSuperheroes(query)

        if (myResponse.isSuccessful) {
            val data = myResponse.body()
            if (data != null) {
                // Log success and update the UI on the main thread
                Log.i("jveApp", "It works")
                withContext(Dispatchers.Main) {
                    presenter.searchSuperheroes(query)
                    binding.progerssBar.isVisible = false
                }
            } else {
                // Log if the data is null
                Log.i("jveApp", "data is null")
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

    // Implement methods from SuperheroContract.View
    override fun showSuperheroes(superheroes: List<SuperheroItemResponse>) {
        adapter.updateList(superheroes)
    }

    /**
     * Placeholder methods. No additional logic needed for showing superhero details.
     */
    override fun showSuperheroDetail(superhero: SuperHeroDetailResponse) {}
    override fun showLoading() {}
    override fun hideLoading() {}
    override fun showError(message: String) {}
}
