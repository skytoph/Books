package com.skytoph.books.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.skytoph.books.data.database.model.BuyLinkEntity

@ProvidedTypeConverter
class LinksConverter(private val gson: Gson) {

    @TypeConverter
    fun fromBuyLinkEntityList(value: List<BuyLinkEntity>?): String? =
        gson.toJson(value)

    @TypeConverter
    fun toBuyLinkEntityList(value: String?): List<BuyLinkEntity>? =
        gson.fromJson(value, object : TypeToken<List<BuyLinkEntity>>() {}.type)
}