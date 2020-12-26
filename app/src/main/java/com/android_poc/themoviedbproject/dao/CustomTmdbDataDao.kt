package com.android_poc.themoviedbproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android_poc.themoviedbproject.pojo.CustomTmdbData


@Dao
interface CustomTmdbDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomTmdbDataIntoTable(customTmdbData: CustomTmdbData)

    @Query("SELECT * FROM CustomTmdbData")
    fun getAllTopMoviesFromTable():LiveData<List<CustomTmdbData>>

    @Query("SELECT * FROM CustomTmdbData ORDER BY releasedDate DESC")
    fun getSortedTopMoviesByReleasedDate():LiveData<List<CustomTmdbData>>

    @Query("SELECT * FROM CustomTmdbData ORDER BY voteCount DESC")
    fun getPopularTopMovies():LiveData<List<CustomTmdbData>>
}