package com.ayan.boruto.data.local

import androidx.room.TypeConverter

class DatabaseConverters {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String{
        val builder = StringBuilder()
        list.forEach {
            builder.append(it).append(",")
        }
        builder.setLength(builder.length - separator.length)
        return builder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String>{
        return string.split(separator)
    }
}