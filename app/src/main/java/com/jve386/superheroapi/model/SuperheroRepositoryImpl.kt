package com.jve386.superheroapi.model

import com.jve386.superheroapi.contract.SuperheroContract
import com.jve386.superheroapi.data.SuperHeroDataResponse
import com.jve386.superheroapi.data.SuperHeroDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.jve386.superheroapi.network.ApiService
import javax.inject.Inject

class SuperheroRepositoryImpl ()
//    @Inject
//    constructor(
//    private val apiService: ApiService,
//    private val apiToken: String
//) : SuperheroRepository(apiService, apiToken)