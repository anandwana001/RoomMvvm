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
                            name = "Akshay Nandwana",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Abdul",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Bunty",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Harsh",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Avish Rawal",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Krishna",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Lokesh Ji",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Raghav",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Prateek",
                            dateOfBirth = Date(959684579)
                        ),
                        User(
                            name = "Vinod",
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