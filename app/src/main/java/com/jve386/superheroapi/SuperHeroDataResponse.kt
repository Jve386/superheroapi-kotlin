package com.jve386.superheroapi

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    // sample - Existing field but not needed for this app: @SerializedName("results-for") val resultsFor: String,
    @SerializedName("results") val superheroes: List<SuperheroItemResponse>
)


data class SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroImage: SuperheroImageResponse,
)

data class SuperheroImageResponse(@SerializedName("url") val url: String)