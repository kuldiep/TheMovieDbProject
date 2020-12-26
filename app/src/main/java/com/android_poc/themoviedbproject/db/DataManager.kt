package com.android_poc.themoviedbproject.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.android_poc.themoviedbproject.pojo.CustomTmdbData
import com.android_poc.themoviedbproject.utils.AppConstants.Companion.TAG
import com.android_poc.themoviedbproject.utils.DatabaseTransactionStatusListener
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

public class DataManager(application: Application) {
    private var tmdbRoomHelper: TmdbRoomHelper

    init {
        tmdbRoomHelper = TmdbRoomHelper.getInstance(application)
    }

    fun insertTopMoviesFromResponse(customTmdbDataList: List<CustomTmdbData>,
                                   databaseTransactionStatusListener: DatabaseTransactionStatusListener) {

        Completable.fromAction {
            for (customTmdbData in customTmdbDataList)
                tmdbRoomHelper.getCustomTmdbDataDao().insertCustomTmdbDataIntoTable(customTmdbData)
        }.subscribeOn(Schedulers.io()).doOnComplete {
            Log.d(TAG, "topMovies inserted successfully ")
            databaseTransactionStatusListener.isDataStoredSuccessfull(true)
        }.subscribe()

    }

    fun getCustomTmdbDataLiveList() : LiveData<List<CustomTmdbData>> {
        return tmdbRoomHelper.getCustomTmdbDataDao().getAllTopMoviesFromTable()
    }

    fun getPopularTopMoviesFromDb():LiveData<List<CustomTmdbData>>{
        return tmdbRoomHelper.getCustomTmdbDataDao().getPopularTopMovies()
    }
    fun getLatestTopMoviesFromDb():LiveData<List<CustomTmdbData>>{
        return tmdbRoomHelper.getCustomTmdbDataDao().getSortedTopMoviesByReleasedDate()
    }
}