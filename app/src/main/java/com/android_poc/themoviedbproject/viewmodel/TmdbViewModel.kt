package com.android_poc.themoviedbproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_poc.themoviedbproject.pojo.CustomTmdbData
import com.android_poc.themoviedbproject.utils.ApiCallStatusListener

class TmdbViewModel : BaseViewModel() {
    private val mutableApiCallListenerFlag = MutableLiveData<Boolean>()

    fun getMutableApiCallListenerFlag():LiveData<Boolean>{
        return mutableApiCallListenerFlag
    }

    fun makeNetworkCall(){
        val apiCallStatusListener = object :ApiCallStatusListener{
            override fun isApiCallSuccessfull(apiFlag: Boolean) {
                mutableApiCallListenerFlag.value = apiFlag
            }
        }
        getRepository().makeApiCall(apiCallStatusListener)
    }

    fun getAllTopMoviesFromRepo():LiveData<List<CustomTmdbData>>{
        return getRepository().getAllTopMoviesFromDb()
    }

    fun getDbStatusListenerFlagFromRepo():LiveData<Boolean>{
        return getRepository().getDbStatusFlagLiveData()
    }
    fun getPopularTopMoviesFromDbByVotesFromRepo():LiveData<List<CustomTmdbData>>{
        return getRepository().getPopularTopMoviesFromDbByVotes()
    }
    fun getLatestTopMoviesFromReleaseDateFromRepo():LiveData<List<CustomTmdbData>>{
        return getRepository().getLatestTopMoviesFromReleaseDate()
    }
}