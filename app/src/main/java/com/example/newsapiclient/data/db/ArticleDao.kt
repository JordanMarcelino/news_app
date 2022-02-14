package com.example.newsapiclient.data.db

import androidx.room.*
import com.example.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM article_table")
    fun getArticle() : Flow<List<Article>>
}