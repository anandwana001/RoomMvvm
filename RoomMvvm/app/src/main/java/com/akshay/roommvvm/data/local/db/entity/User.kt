package com.akshay.roommvvm.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "date_of_birth")
    var dateOfBirth: Date,

    @Ignore
    var selected: Boolean = false
) {
    constructor() : this(0, "", Date(), false)
}