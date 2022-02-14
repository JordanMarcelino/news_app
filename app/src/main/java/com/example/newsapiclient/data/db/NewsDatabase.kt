package com.example.newsapiclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapiclient.data.model.Article

@Database(
    entities = [Article::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converter::class)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun getArticleDao() : ArticleDao
}