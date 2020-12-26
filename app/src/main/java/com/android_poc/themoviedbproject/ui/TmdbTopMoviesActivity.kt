package com.android_poc.themoviedbproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.android_poc.themoviedbproject.R
import com.android_poc.themoviedbproject.utils.AppConstants
import com.android_poc.themoviedbproject.viewmodel.TmdbViewModel

class TmdbTopMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmdb_top_movies)
        val tmdbViewModel by viewModels<TmdbViewModel>()
        tmdbViewModel.makeNetworkCall()
        tmdbViewModel.getAllTopMoviesFromRepo().observe(this, {
            Log.d(AppConstants.TAG, "onCreate: customTmdbDataList is = "+it)
        })
        tmdbViewModel.getLatestTopMoviesFromReleaseDateFromRepo().observe(this, {
            Log.d(AppConstants.TAG, "onCreate: latest movies by date is = "+it)
        })
        tmdbViewModel.getPopularTopMoviesFromDbByVotesFromRepo().observe(this, {
            Log.d(AppConstants.TAG, "onCreate: popular movies by vote is = "+it)
        })
    }
}