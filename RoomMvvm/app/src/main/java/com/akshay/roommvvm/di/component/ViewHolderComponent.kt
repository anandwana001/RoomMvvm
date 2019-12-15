package com.akshay.roommvvm.di.component

import com.akshay.roommvvm.di.module.ViewHolderModule
import com.akshay.roommvvm.ui.main.user.UserItemViewHolder
import com.mindorks.bootcamp.instagram.di.ViewModelScope
import dagger.Component

/**
 * Created by akshaynandwana on
 * 15, December, 2019
 **/

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(userItemViewHolder: UserItemViewHolder)
}