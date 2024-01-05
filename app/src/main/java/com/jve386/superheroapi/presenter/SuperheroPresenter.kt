package com.jve386.superheroapi.presenter

import com.jve386.superheroapi.contract.SuperheroContract
import com.jve386.superheroapi.data.SuperheroItemResponse
import com.jve386.superheroapi.data.SuperHeroDataResponse
import com.jve386.superheroapi.data.SuperHeroDetailResponse
import com.jve386.superheroapi.model.SuperheroRepository

class SuperheroPresenter(private val repository: SuperheroRepository) : SuperheroContract.Presenter {

    private var view: SuperheroContract.View? = null

    override fun attachView(view: SuperheroContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override suspend fun searchSuperheroes(name: String) {
        // Implement logic to fetch superheroes by name
        // Use the repository to make API calls
        // Update the view accordingly

        view?.showLoading()

        repository.searchSuperheroes(name, object : SuperheroRepository.Callback<SuperHeroDataResponse> {
            override fun onSuccess(response: SuperHeroDataResponse) {
                view?.hideLoading()
                view?.showSuperheroes(response.superheroes)
            }

            override fun onError(message: String) {
                view?.hideLoading()
                view?.showError(message)
            }
        })
    }

    override suspend fun getSuperheroDetail(id: String) {
        // Implement logic to fetch superhero details by ID
        // Use the repository to make API calls
        // Update the view accordingly

        view?.showLoading()

        repository.getSuperheroDetail(id, object : SuperheroRepository.Callback<SuperHeroDetailResponse> {
            override fun onSuccess(response: SuperHeroDetailResponse) {
                view?.hideLoading()
                view?.showSuperheroDetail(response)
            }

            override fun onError(message: String) {
                view?.hideLoading()
                view?.showError(message)
            }
        })
    }
}