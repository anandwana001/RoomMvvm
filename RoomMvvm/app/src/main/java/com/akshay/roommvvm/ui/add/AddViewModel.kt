package com.akshay.roommvvm.ui.add

import androidx.lifecycle.MutableLiveData
import com.akshay.roommvvm.ui.base.BaseViewModel
import com.akshay.roommvvm.utils.common.Event

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/
class AddViewModel : BaseViewModel() {

    val nameField: MutableLiveData<String> = MutableLiveData()
    val prevNameField: MutableLiveData<String> = MutableLiveData()
    val userIdField: MutableLiveData<Long> = MutableLiveData()

    val replyBackAdded: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val replyBackDeleted: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {}

    fun onNameChange(email: String) = nameField.postValue(email)
    fun onPrevNameChange(name: String) = prevNameField.postValue(name)
    fun updateUserId(id: Long) = userIdField.postValue(id)

    fun replyToAddDatabase() {
        replyBackAdded.postValue(Event(emptyMap()))
    }

    fun replyToDeleteDatabase() {
        replyBackDeleted.postValue(Event(emptyMap()))
    }
}