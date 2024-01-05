package com.jve386.superheroapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.jve386.superheroapi.data.PowerStatsResponse
import com.jve386.superheroapi.data.SuperHeroDetailResponse
import com.jve386.superheroapi.network.ApiService
import com.jve386.superheroapi.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve superhero ID from intent
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

        // Call function to fetch and display superhero information
        getSuperheroInformation(id)
    }

    // Function to create UI elements based on superhero details
    private fun createUI(superhero: SuperHeroDetailResponse) {
        // Load superhero image using Picasso library
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)

        // Set superhero name
        binding.tvSuperheroName.text = superhero.name

        // Prepare and set superhero power stats
        prepareStats(superhero.powerstats)

        // Set superhero real name and publisher
        binding.tvSuperheroRealName.text = superhero.biography.fullname
        binding.tvSuperheroPublisher.text = superhero.biography.publisher

        // Replace commas and semicolons with line breaks in the connections string
        val formattedConnections = superhero.connections.groupaffiliation
            .replace(", ", "\n")
            .replace("; ", "\n")

        // Set superhero group affiliation with formatted connections
        binding.tvSuperheroGroupAffiliation.text = formattedConnections
    }

    // Function to prepare and set superhero power stats
    private fun prepareStats(powerstats: PowerStatsResponse) {
        // Update the height of stat bars based on powerstats
        updateHeight(binding.viewIntelligence, powerstats.intelligence.toInt())
        updateHeight(binding.viewStrength, powerstats.strength.toInt())
        updateHeight(binding.viewSpeed, powerstats.speed.toInt())
        updateHeight(binding.viewDurability, powerstats.durability.toInt())
        updateHeight(binding.viewPower, powerstats.power.toInt())
        updateHeight(binding.viewCombat, powerstats.combat.toInt())
    }

    // Function to update the height of a view based on a given stat value
    private fun updateHeight(view: View, stat: Int) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    // Function to convert pixels to density-independent pixels (dp)
    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            px,
            resources.displayMetrics
        ).roundToInt()
    }

    // Function to get superhero information using Retrofit
    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            // Make a network request to get superhero details
            val superheroDetail = getRetrofit().create(ApiService::class.java).getSuperheroeDetail(id)

            // Check if the response body is not null
            if (superheroDetail.body() != null) {
                // Update UI on the main thread with the superhero details
                runOnUiThread { createUI(superheroDetail.body()!!) }
            }
        }
    }

    // Function to create a Retrofit instance
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
