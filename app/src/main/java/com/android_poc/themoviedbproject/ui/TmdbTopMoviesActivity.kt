package com.android_poc.themoviedbproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android_poc.themoviedbproject.R
import com.android_poc.themoviedbproject.databinding.ActivityTmdbTopMoviesBinding
import com.android_poc.themoviedbproject.utils.AppConstants
import com.android_poc.themoviedbproject.viewmodel.TmdbViewModel

class TmdbTopMoviesActivity : AppCompatActivity() {
    private lateinit var viewModel: TmdbViewModel
    private var binding:ActivityTmdbTopMoviesBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tmdb_top_movies)
        viewModel = ViewModelProvider(this).get(TmdbViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.setMenuItemSelection(item.title.toString())
        return super.onOptionsItemSelected(item)
    }
}