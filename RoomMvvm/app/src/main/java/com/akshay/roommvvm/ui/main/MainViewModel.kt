package com.akshay.roommvvm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akshay.roommvvm.data.local.db.entity.User
import com.akshay.roommvvm.data.repository.UserRepository
import com.akshay.roommvvm.ui.base.BaseViewModel
import com.akshay.roommvvm.utils.common.Event
import kotlinx.coroutines.launch

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/
class MainViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val allUser = MutableLiveData<List<User>>()
    val launchAddData: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        fillDataToDatabase()
    }

    fun fillDataToDatabase() {
        viewModelScope.launch {
            userRepository.fillDatabase()
            getAllUser()
        }
    }

    suspend fun getAllUser() {
        allUser.value = userRepository.returnAllUsers()
    }

    fun launchAddDataActivity() {
        launchAddData.postValue(Event(emptyMap()))
    }

    fun addDataToDatabase(name: String) {
        viewModelScope.launch {
            userRepository.addSingleDataToDatabase(name)
            getAllUser()
        }
    }

    fun deleteUser(userIdField: Long) {
        viewModelScope.launch {
            userRepository.deleteUser(userIdField)
            getAllUser()
        }
    }
}