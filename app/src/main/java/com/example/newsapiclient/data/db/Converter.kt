package com.example.newsapiclient.data.db

import androidx.room.TypeConverter
import com.example.newsapiclient.data.model.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source) : String = source.name!!

    @TypeConverter
    fun toSource(name : String) : Source = Source(name, name)
}