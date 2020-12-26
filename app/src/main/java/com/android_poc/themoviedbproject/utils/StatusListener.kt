package com.android_poc.themoviedbproject.utils

interface ApiCallStatusListener {
    fun isApiCallSuccessfull(apiFlag:Boolean)
}

interface DatabaseTransactionStatusListener{
    fun isDataStoredSuccessfull(dbFlag: Boolean = false)
}