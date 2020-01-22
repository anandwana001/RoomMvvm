package com.akshay.roommvvm.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.akshay.roommvvm.DatabaseApplication
import com.akshay.roommvvm.data.local.db.DatabaseService
import com.mindorks.bootcamp.instagram.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/

@Module
class ApplicationModule(private val application: DatabaseApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * We need to write @Singleton on the provide method if we create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */
    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "room-mvvm-project-db"
        ).build()

}