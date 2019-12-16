package com.akshay.roommvvm.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.akshay.roommvvm.data.local.db.entity.User
import com.akshay.roommvvm.data.repository.UserRepository
import com.akshay.roommvvm.ui.base.BaseViewModel
import com.akshay.roommvvm.utils.common.Event
import com.akshay.roommvvm.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/
class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val allUser = MutableLiveData<List<User>>()
    val launchAddData: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        fillDataToDatabase()
    }

    private fun fillDataToDatabase() {
        compositeDisposable.addAll(
            userRepository.fillDatabase()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        getAllUser()
                    },
                    {}
                )
        )
    }

    fun getAllUser() {
        compositeDisposable.addAll(
            userRepository.returnAllUsers()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        allUser.postValue(it)
                    },
                    {}
                )
        )
    }

    fun launchAddDataActivity() {
        launchAddData.postValue(Event(emptyMap()))
    }

}