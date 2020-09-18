package com.example.currencyConverter.domain.entity.db

import androidx.room.TypeConverter
import com.example.currencyConverter.domain.entity.models.Valute
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ValuteMapConverter {
    @TypeConverter
    fun fromValute(valuteMap: Map<String, Valute>): String {
        return Gson().toJson(valuteMap)
    }

    inline fun <reified T> parseMap(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }

    @TypeConverter
    fun toValute(valute: String): Map<String, Valute> {
        return parseMap<Map<String, Valute>>(
            valute,
            object : TypeToken<Map<String, Valute>>() {}.type
        )
    }
}