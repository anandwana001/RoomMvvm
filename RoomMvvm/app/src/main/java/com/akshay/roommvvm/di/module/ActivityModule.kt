package com.akshay.roommvvm.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.akshay.roommvvm.data.repository.UserRepository
import com.akshay.roommvvm.ui.add.AddViewModel
import com.akshay.roommvvm.ui.base.BaseActivity
import com.akshay.roommvvm.ui.main.MainViewModel
import com.akshay.roommvvm.ui.main.user.UserAdapter
import com.akshay.roommvvm.utils.ViewModelProviderFactory
import com.akshay.roommvvm.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager =
        LinearLayoutManager( activity)

    @Provides
    fun provideUserAdapter(): UserAdapter = UserAdapter(activity.lifecycle, ArrayList())

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userRepository: UserRepository
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, userRepository)
        }).get(MainViewModel::class.java)

    @Provides
    fun provideAddViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userRepository: UserRepository
    ): AddViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(AddViewModel::class) {
            AddViewModel(schedulerProvider, compositeDisposable, userRepository)
        }).get(AddViewModel::class.java)
}