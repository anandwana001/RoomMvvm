package com.akshay.roommvvm.ui.add

import androidx.lifecycle.MutableLiveData
import com.akshay.roommvvm.data.repository.UserRepository
import com.akshay.roommvvm.ui.base.BaseViewModel
import com.akshay.roommvvm.utils.common.Event
import com.akshay.roommvvm.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/
class AddViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val nameField: MutableLiveData<String> = MutableLiveData()
    val prevNameField: MutableLiveData<String> = MutableLiveData()
    val userIdField: MutableLiveData<Long> = MutableLiveData()

    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {}

    fun onNameChange(email: String) = nameField.postValue(email)
    fun onPrevNameChange(name: String) = prevNameField.postValue(name)
    fun updateUserId(id: Long) = userIdField.postValue(id)

    fun addDataToDatabase() {

        val name = nameField.value

        compositeDisposable.addAll(
            userIdField.value?.let {
                userRepository.updateUser(it, name ?: "Update")
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            launchMain.postValue(Event(emptyMap()))
                        },
                        {}
                    )
            } ?: kotlin.run {
                userRepository.addSingleDataToDatabase(name ?: "Akshay")
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            launchMain.postValue(Event(emptyMap()))
                        },
                        {}
                    )
            }
        )
    }

    fun deleteUser() {
        userIdField.value?.let {
            compositeDisposable.addAll(
                userRepository.deleteUser(it)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            launchMain.postValue(Event(emptyMap()))
                        },
                        {}
                    )
            )
        }
    }
}