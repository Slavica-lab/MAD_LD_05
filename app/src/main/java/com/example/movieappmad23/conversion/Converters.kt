package com.example.movieappmad23.conversion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListString(list: List<String>?): String? {
        if (list == null) {
            return ""
        }
        return list.joinToString(separator = "||")
    }

    @TypeConverter
    fun toStringList(string: String?): List<String>? {
        if (string == null) {
            return listOf()
        }
        return string.split("||").toList()
    }

    @TypeConverter
    fun toBoolean(mutableStateBoolean: MutableState<Boolean>?): Boolean? {
        if (mutableStateBoolean == null) {
            return null
        }
        return mutableStateBoolean.value
    }

    @TypeConverter
    fun toMutableStateBoolean(boolean: Boolean?): MutableState<Boolean>? {
        if (boolean == null) {
            return null
        }
        return mutableStateOf(boolean)
    }
}