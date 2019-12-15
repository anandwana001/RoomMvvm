package com.akshay.roommvvm.data.local.db.dao

import androidx.room.*
import com.akshay.roommvvm.data.local.db.entity.User
import io.reactivex.Single


@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Single<Long>

    @Update
    fun update(user: User): Single<Int>

    @Delete
    fun delete(user: User): Single<Int>

    @Query("DELETE FROM users")
    fun deleteAllUsers(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(vararg users: User): Single<List<Long>>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<User>>

    @Query("SELECT * from users where id = :id LIMIT 1")
    fun getUserById(id: Long): Single<User>

    @Query("SELECT count(*) from users")
    fun count(): Single<Int>

    @Query("UPDATE users SET name = :name WHERE id = :id")
    fun updateUser(id: Long, name: String): Single<Int>

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUserById(id: Long): Single<Int>
}