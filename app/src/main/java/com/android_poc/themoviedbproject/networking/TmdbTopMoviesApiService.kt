package com.android_poc.themoviedbproject.networking

import com.android_poc.themoviedbproject.tos.TmdbRootRespTO
import com.android_poc.themoviedbproject.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbTopMoviesApiService {

    @GET(AppConstants.END_POINT)
    fun getTopMoviesFromApi():Call<TmdbRootRespTO>
}