package com.akhilsreekar.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == Resource.Status.SUCCESS) {
//            emitSource(MutableLiveData(responseStatus))
            emit(Resource.success(responseStatus.data!!))
        } else if(responseStatus.status == Resource.Status.ERROR){
            emit(Resource.error(responseStatus.message!!))
//            emitSource(source)
        }


    }