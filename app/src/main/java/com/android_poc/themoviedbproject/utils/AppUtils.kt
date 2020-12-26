package com.android_poc.themoviedbproject.utils

import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    fun getMilisecondFromDate(strDate:String) : Long{
        if(strDate.isNotEmpty()) {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date: Date = simpleDateFormat.parse(strDate)
            return date.time
        }
        return 0
    }

    fun getFullImgPath(strPath:String) : String?{
        if(strPath.isNotEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(AppConstants.IMG_PATH).append(strPath)
            return stringBuilder.toString()
        }
        return null

    }
}
