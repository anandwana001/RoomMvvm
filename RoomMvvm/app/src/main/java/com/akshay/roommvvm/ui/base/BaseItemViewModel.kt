package com.akshay.roommvvm.ui.base

import androidx.lifecycle.MutableLiveData

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/

abstract class BaseItemViewModel<T : Any> : BaseViewModel() {

    val data: MutableLiveData<T> = MutableLiveData()

    fun onManualCleared() = onCleared()

    fun updateData(data: T) {
        this.data.postValue(data)
    }
}