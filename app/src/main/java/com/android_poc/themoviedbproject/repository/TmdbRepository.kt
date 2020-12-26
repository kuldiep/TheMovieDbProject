package com.android_poc.themoviedbproject.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_poc.themoviedbproject.TmdbApplication
import com.android_poc.themoviedbproject.db.DataManager
import com.android_poc.themoviedbproject.networking.TMDBRetrofitClient
import com.android_poc.themoviedbproject.networking.TmdbTopMoviesApiService
import com.android_poc.themoviedbproject.pojo.CustomTmdbData
import com.android_poc.themoviedbproject.tos.Results
import com.android_poc.themoviedbproject.tos.TmdbRootRespTO
import com.android_poc.themoviedbproject.utils.ApiCallStatusListener
import com.android_poc.themoviedbproject.utils.AppConstants.Companion.TAG
import com.android_poc.themoviedbproject.utils.AppUtils
import com.android_poc.themoviedbproject.utils.DatabaseTransactionStatusListener
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TmdbRepository private constructor() {

    private val dataManager = DataManager(TmdbApplication.getApplicationInstance())
    private val mutableDbStatusListener = MutableLiveData<Boolean>()
    private object HOLDER {
        val INSTANCE = TmdbRepository()
    }

    companion object {
        fun getInstance(): TmdbRepository {
            val mInstance by lazy { HOLDER.INSTANCE }
            return mInstance
        }
    }

    fun makeApiCall(apiCallStatusListener: ApiCallStatusListener) {
        val tmdbTopMoviesApiService = TMDBRetrofitClient.buildService(TmdbTopMoviesApiService::class.java)
        tmdbTopMoviesApiService.getTopMoviesFromApi().enqueue(object : Callback<TmdbRootRespTO> {
            override fun onResponse(call: Call<TmdbRootRespTO>, response: Response<TmdbRootRespTO>) {
                if (response.isSuccessful) {
                    apiCallStatusListener.isApiCallSuccessfull(true)
                    insertResultListFromResponseIntoDb(response.body()?.results)
                }
                else
                    apiCallStatusListener.isApiCallSuccessfull(false)
            }

            override fun onFailure(call: Call<TmdbRootRespTO>, t: Throwable) {
                apiCallStatusListener.isApiCallSuccessfull(false)
            }
        })
    }

    fun insertResultListFromResponseIntoDb(resultList: List<Results>?){
        val databaseTransactionStatusListener = object:DatabaseTransactionStatusListener{
            override fun isDataStoredSuccessfull(dbFlag: Boolean) {
                mutableDbStatusListener.postValue(dbFlag)
            }
        }
        try{
            if (resultList != null) {
                val customTmdbDataList = arrayListOf<CustomTmdbData>()
                for(result in resultList){
                    val customTmdbData = CustomTmdbData()
                    customTmdbData.title = result.title
                    customTmdbData.description = result.overview
                    customTmdbData.displayDate = result.release_date
                    customTmdbData.popularityCount = result.popularity.toLong()
                    customTmdbData.voteCount = result.vote_count
                    customTmdbData.releasedDate = AppUtils.getMilisecondFromDate(result.release_date)
                    customTmdbData.posterImgUrl = AppUtils.getFullImgPath(result.poster_path)
                    customTmdbDataList.add(customTmdbData)
                }
                dataManager.insertTopMoviesFromResponse(customTmdbDataList,databaseTransactionStatusListener)
            }
        }catch (ex:Exception){
            Log.e(TAG, "insertResultListFromResponseIntoDb: exception ..",ex)
        }
    }

    fun getAllTopMoviesFromDb():LiveData<List<CustomTmdbData>>{
        return dataManager.getCustomTmdbDataLiveList()
    }

    fun getDbStatusFlagLiveData():LiveData<Boolean>{
        return mutableDbStatusListener
    }
    fun getPopularTopMoviesFromDbByVotes():LiveData<List<CustomTmdbData>>{
        return dataManager.getPopularTopMoviesFromDb()
    }
    fun getLatestTopMoviesFromReleaseDate():LiveData<List<CustomTmdbData>>{
        return dataManager.getLatestTopMoviesFromDb()
    }
}