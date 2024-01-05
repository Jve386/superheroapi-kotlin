package com.jve386.superheroapi.di

import com.jve386.superheroapi.contract.SuperheroContract
import com.jve386.superheroapi.model.SuperheroRepository
import com.jve386.superheroapi.presenter.SuperheroPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object PresenterModule {

    @Provides
    @ActivityScoped
    fun providePresenter(repository: SuperheroRepository): SuperheroContract.Presenter {
        return SuperheroPresenter(repository)
    }
}