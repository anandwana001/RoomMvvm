package com.akshay.roommvvm.di.component

import android.app.Application
import android.content.Context
import com.akshay.roommvvm.DatabaseApplication
import com.akshay.roommvvm.data.local.db.DatabaseService
import com.akshay.roommvvm.data.repository.UserRepository
import com.akshay.roommvvm.di.module.ApplicationModule
import com.akshay.roommvvm.utils.rx.SchedulerProvider
import com.mindorks.bootcamp.instagram.di.ApplicationContext
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: DatabaseApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    /**
     * These methods are written in ApplicationComponent because the instance of
     * NetworkService is singleton and is maintained in the ApplicationComponent's implementation by Dagger
     * For NetworkService singleton instance to be accessible to say DummyActivity's DummyViewModel
     * this ApplicationComponent must expose a method that returns NetworkService instance
     * This method will be called when NetworkService is injected in DummyViewModel.
     * Also, in ActivityComponent you can find dependencies = [ApplicationComponent::class] to link this relationship
     */
    fun getDatabaseService(): DatabaseService


    /**---------------------------------------------------------------------------
     * Dagger will internally create UserRepository instance using constructor injection.
     * Dependency through constructor
     * UserRepository ->
     *  [DatabaseService -> Nothing is required]
     * So, Dagger will be able to create an instance of UserRepository by its own using constructor injection
     *---------------------------------------------------------------------------------
     */
    fun getUserRepository(): UserRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

}