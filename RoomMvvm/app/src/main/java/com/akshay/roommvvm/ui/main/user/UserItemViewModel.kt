package com.akshay.roommvvm.ui.main.user


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.akshay.roommvvm.data.local.db.entity.User
import com.akshay.roommvvm.ui.base.BaseItemViewModel
import com.akshay.roommvvm.utils.common.Event
import com.akshay.roommvvm.utils.common.TimeUtils
import com.akshay.roommvvm.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/

class UserItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseItemViewModel<User>(schedulerProvider, compositeDisposable) {

    companion object {
        const val TAG = "UserItemViewModel"
    }

    val launchAddData: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val name: LiveData<String> = Transformations.map(data) {
        it.name
    }

    val dateOfBirth: LiveData<String> = Transformations.map(data) {
        TimeUtils.getTimeAgo(it.dateOfBirth)
    }

    val userId: LiveData<Long> = Transformations.map(data) {
        it.id
    }

    override fun onCreate() {}

    fun launchAddDataActivity() {
        launchAddData.postValue(Event(emptyMap()))
    }

}