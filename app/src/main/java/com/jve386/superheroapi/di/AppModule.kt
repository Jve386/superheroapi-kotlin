package com.jve386.superheroapi.di

import android.content.Context
import com.jve386.superheroapi.contract.SuperheroContract
import com.jve386.superheroapi.model.SuperheroRepository
import com.jve386.superheroapi.network.ApiService
import com.jve386.superheroapi.presenter.SuperheroPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.IOException
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiToken(@ApplicationContext context: Context): String {
        val properties = Properties()
        return try {
            val inputStream = context.assets.open("config.properties")
            properties.load(inputStream)
            properties.getProperty("api.token") ?: throw IllegalStateException("api.token is null")
        } catch (e: IOException) {
            // Log the error or handle it according to your application's requirements
            throw IllegalStateException("Error reading api token", e)
        }
    }

    @Provides
    fun provideApiService(apiToken: String): ApiService {
        return ApiService.create(apiToken)
    }

}


