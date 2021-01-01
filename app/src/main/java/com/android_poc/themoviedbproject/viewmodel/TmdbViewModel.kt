package com.android_poc.themoviedbproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_poc.themoviedbproject.R
import com.android_poc.themoviedbproject.pojo.CustomTmdbData
import com.android_poc.themoviedbproject.utils.ApiCallStatusListener

class TmdbViewModel : BaseViewModel() {
    private val mutableApiCallListenerFlag = MutableLiveData<Boolean>()
    private var mutableMenuItemSelectionLiveData = MutableLiveData<String>()

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
    fun getSortedMoviesBySeletedItemFromDb(sortValue : String):LiveData<List<CustomTmdbData>>?{
        if(sortValue.contentEquals("Sort by Vote")){
            return getRepository().getPopularTopMoviesFromDbByVotes()
        }else if(sortValue.contentEquals("Sort by Date")) {
            return getRepository().getLatestTopMoviesFromReleaseDate()
        }
        return null
    }

    fun setMenuItemSelection(menuSelection:String){
        mutableMenuItemSelectionLiveData.value = menuSelection
    }
    fun observeMenuItemSelectionInFragment():LiveData<String>{
        return mutableMenuItemSelectionLiveData
    }
}