package com.jve386.superheroapi.contract

import com.jve386.superheroapi.data.SuperHeroDetailResponse
import com.jve386.superheroapi.data.SuperheroItemResponse

interface SuperheroContract {
    interface View {
        fun showSuperheroes(superheroes: List<SuperheroItemResponse>)
        fun showSuperheroDetail(superhero: SuperHeroDetailResponse)
        fun showLoading()
        fun hideLoading()
        fun showError(message: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        suspend fun searchSuperheroes(name: String)
        suspend fun getSuperheroDetail(id: String)
    }
}