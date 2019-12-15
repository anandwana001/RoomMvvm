package com.akshay.roommvvm.di.module

import androidx.lifecycle.LifecycleRegistry
import com.akshay.roommvvm.ui.base.BaseItemViewHolder
import com.mindorks.bootcamp.instagram.di.ViewModelScope
import dagger.Module
import dagger.Provides

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/
@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}