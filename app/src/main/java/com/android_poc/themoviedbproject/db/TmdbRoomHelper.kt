package com.android_poc.themoviedbproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android_poc.themoviedbproject.dao.CustomTmdbDataDao
import com.android_poc.themoviedbproject.pojo.CustomTmdbData

@Database(entities = arrayOf(CustomTmdbData::class),version = 1,exportSchema = false)
abstract class TmdbRoomHelper:RoomDatabase() {

    abstract fun getCustomTmdbDataDao(): CustomTmdbDataDao

    companion object {
        private var mInstance: TmdbRoomHelper? = null
        fun getInstance(context: Context): TmdbRoomHelper {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                        context.applicationContext, TmdbRoomHelper::class.java, "TmdbRoomHelper"
                ).build()
            }
            return mInstance as TmdbRoomHelper
        }
    }
}