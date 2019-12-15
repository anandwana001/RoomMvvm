package com.akshay.roommvvm.data.repository

import com.akshay.roommvvm.data.local.db.DatabaseService
import com.akshay.roommvvm.data.local.db.entity.User
import io.reactivex.Single
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

    fun fillDatabase(): Single<Any> = databaseService.userDao()
        .count()
        .flatMap {
            if (it == 0)
                databaseService.userDao()
                    .insertMany(
                        User(
                            name = "Test 1",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 2",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 3",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 4",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 5",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 6",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 7",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 8",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 9",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Test 10",
                            dateOfBirth = Date(959684579)
                        )
                    )
            else Single.just(0)
        }

    fun returnAllUsers() = databaseService.userDao().getAllUsers()

    fun deleteUser(id: Long) = databaseService.userDao().deleteUserById(id)

    fun addSingleDataToDatabase(name: String): Single<Long> =
        databaseService.userDao()
            .insert(
                User(
                    name = name,
                    dateOfBirth = Date(959684579)
                )
            )

    fun updateUser(id: Long, name: String): Single<Int> =
        databaseService.userDao().updateUser(id, name)
}