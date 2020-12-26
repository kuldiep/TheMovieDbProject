package com.android_poc.themoviedbproject.viewmodel

import androidx.lifecycle.ViewModel
import com.android_poc.themoviedbproject.repository.TmdbRepository

open class BaseViewModel : ViewModel() {

    fun getRepository():TmdbRepository{
        return TmdbRepository.getInstance()
    }
}