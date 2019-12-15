package com.akshay.roommvvm

import android.app.Application
import com.akshay.roommvvm.di.component.ApplicationComponent
import com.akshay.roommvvm.di.component.DaggerApplicationComponent
import com.akshay.roommvvm.di.module.ApplicationModule

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/
class DatabaseApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}