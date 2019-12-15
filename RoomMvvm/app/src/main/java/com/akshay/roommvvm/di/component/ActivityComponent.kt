package com.akshay.roommvvm.di.component

import com.akshay.roommvvm.di.module.ActivityModule
import com.akshay.roommvvm.ui.add.AddActivity
import com.akshay.roommvvm.ui.main.MainActivity
import com.mindorks.bootcamp.instagram.di.ActivityScope
import dagger.Component

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: AddActivity)
}