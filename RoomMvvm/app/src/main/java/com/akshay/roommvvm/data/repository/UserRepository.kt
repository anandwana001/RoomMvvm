package com.akshay.roommvvm.data.repository

import com.akshay.roommvvm.data.local.db.DatabaseService
import com.akshay.roommvvm.data.local.db.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 14, December, 2019
 **/

@Singleton
class UserRepository @Inject constructor(
    private val databaseService: DatabaseService
) {

    suspend fun fillDatabase() {
        withContext(Dispatchers.Default) {

            val count = databaseService.userDao().count()

            if (count == 0) {
                databaseService.userDao()
                    .insertMany(
                        User(
                            name = "Akshay Nandwana",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Abdul",
                            dateOfBirth = Date(959684579)
                        )
                    )
            }
        }
    }

    suspend fun returnAllUsers(): List<User> {
        return withContext(Dispatchers.Default) {
            databaseService.userDao().getAllUsers()
        }
    }

    suspend fun deleteUser(id: Long) {
        withContext(Dispatchers.Default) {
            databaseService.userDao().deleteUserById(id)
        }
    }

    suspend fun addSingleDataToDatabase(name: String) {
        withContext(Dispatchers.Default) {
            databaseService.userDao()
                .insert(
                    User(
                        name = name,
                        dateOfBirth = Date(959684579)
                    )
                )
        }
    }
}