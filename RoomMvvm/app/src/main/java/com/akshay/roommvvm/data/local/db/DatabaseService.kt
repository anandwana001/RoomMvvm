package com.akshay.roommvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akshay.roommvvm.data.local.db.dao.UserDao
import com.akshay.roommvvm.data.local.db.entity.User
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/

@Singleton
@Database(
    entities = [
        User::class
    ],
    exportSchema = false,
    version = 2
)
@TypeConverters(Converter::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun userDao(): UserDao

}