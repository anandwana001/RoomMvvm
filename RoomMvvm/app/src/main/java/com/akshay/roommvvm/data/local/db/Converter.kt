package com.akshay.roommvvm.data.local.db

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?) = date?.time
}