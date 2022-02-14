package com.example.newsapiclient.data.model


import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName


data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)