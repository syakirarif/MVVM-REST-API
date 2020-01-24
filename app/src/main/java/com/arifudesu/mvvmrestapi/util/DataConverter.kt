package com.arifudesu.mvvmrestapi.util

import androidx.room.TypeConverter
import com.arifudesu.mvvmrestapi.model.Genre
import com.arifudesu.mvvmrestapi.model.Producer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    companion object {
        @JvmStatic
        @TypeConverter
        fun fromGenre(value: List<Genre>): String {
            val gson = Gson()
//            val type = object : TypeToken<List<WpFeaturedmedia>>() {}.type
            return gson.toJson(value)
        }

        @JvmStatic
        @TypeConverter
        fun toGenre(value: String?): List<Genre> {
            val gson = Gson()
            if (value == null) {
                return ArrayList()
            }
            val type = object : TypeToken<List<Genre>>() {}.type
            return gson.fromJson<List<Genre>>(value, type)
        }

        @JvmStatic
        @TypeConverter
        fun fromProducer(value: List<Producer>): String {
            val gson = Gson()
//            val type = object : TypeToken<List<WpFeaturedmedia>>() {}.type
            return gson.toJson(value)
        }

        @JvmStatic
        @TypeConverter
        fun toProducer(value: String?): List<Producer> {
            val gson = Gson()
            if (value == null) {
                return ArrayList()
            }
            val type = object : TypeToken<List<Producer>>() {}.type
            return gson.fromJson<List<Producer>>(value, type)
        }
    }


}