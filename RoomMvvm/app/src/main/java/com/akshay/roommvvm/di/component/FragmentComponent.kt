package com.akshay.roommvvm.di.component

import com.akshay.roommvvm.di.module.FragmentModule
import com.mindorks.bootcamp.instagram.di.FragmentScope
import dagger.Component

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

}