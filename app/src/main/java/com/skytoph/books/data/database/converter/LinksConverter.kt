package com.skytoph.books.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.skytoph.books.data.database.model.BuyLinkEntity

class LinksConverter(private val gson: Gson) {

    @TypeConverter
    fun fromString(value: String?): BuyLinkEntity = gson.fromJson(value, BuyLinkEntity::class.java)

    @TypeConverter
    fun fromFrequency(link: BuyLinkEntity): String = gson.toJson(link, BuyLinkEntity::class.java)
}