package com.cloud_evalutaion.data.local.database

import androidx.room.TypeConverter
import com.cloud_evalutaion.model.FieldOption
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FieldOptionConverter {
    @TypeConverter
    fun fromFieldOptionList(value: List<FieldOption>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toFieldOptionList(value: String): List<FieldOption>? {
        val listType = object : TypeToken<List<FieldOption>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
