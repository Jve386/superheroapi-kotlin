package com.jve386.superheroapi.model

import com.jve386.superheroapi.contract.SuperheroContract
import com.jve386.superheroapi.data.SuperHeroDataResponse
import com.jve386.superheroapi.data.SuperHeroDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.jve386.superheroapi.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.resume
import javax.inject.Inject

class SuperheroRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiToken: String
) {

    interface Callback<T> {
        fun onSuccess(response: T)
        fun onError(message: String)
    }

    suspend fun searchSuperheroes(name: String, callback: Callback<SuperHeroDataResponse>) {
        try {
            val response = apiService.getSuperheroes(apiToken, name)
            if (response.isSuccessful) {
                callback.onSuccess(response.body()!!)
            } else {
                callback.onError("Failed to fetch superheroes")
            }
        } catch (e: Exception) {
            callback.onError("Error: ${e.message}")
        }
    }

    suspend fun getSuperheroDetail(id: String, callback: Callback<SuperHeroDetailResponse>) {
        try {
            val response = apiService.getSuperheroDetail(apiToken, id)
            if (response.isSuccessful) {
                callback.onSuccess(response.body()!!)
            } else {
                callback.onError("Failed to fetch superhero details")
            }
        } catch (e: Exception) {
            callback.onError("Error: ${e.message}")
        }
    }
}