package com.jve386.superheroapi

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperheroImageDetailResponse,
    @SerializedName("biography") val biography: SuperheroBiographyResponse,
   @SerializedName("connections") val connections: SuperheroConnectionsResponse
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,
)

data class SuperheroImageDetailResponse(@SerializedName("url") val url: String)

data class SuperheroBiographyResponse(
    @SerializedName("full-name") val fullname: String,
    @SerializedName("publisher") val publisher: String
)

data class SuperheroConnectionsResponse(
    @SerializedName("group-affiliation") val groupaffiliation: String
)