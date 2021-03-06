package com.raccoon.flags.api

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Service with REST API for making requests
 */
interface ApiService {

    companion object {
        const val apiEndpoint = "https://restcountries.eu/rest/v2/"
    }

    @GET("all")
    fun countriesList(): Single<List<DataModel>>

}