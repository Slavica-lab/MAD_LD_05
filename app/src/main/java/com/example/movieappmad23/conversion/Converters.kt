package com.example.movieappmad23.conversion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.TypeConverter
import com.example.movieappmad23.models.Genre
import kotlin.streams.toList

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

    @TypeConverter
    fun toString(list: List<Genre>?): String? {
        if (list == null) {
            return null
        }
        return list.joinToString(separator = "||")
    }

    @TypeConverter
    fun toListGenre(string: String?): List<Genre>? {
        if (string == null) {
            return null
        }
        return string.split("||").stream().map { Genre.valueOf(it) }.toList()
    }
}