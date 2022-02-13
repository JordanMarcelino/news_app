package com.example.newsapiclient.data.api

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsServiceTest {

    private lateinit var newsService: NewsService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        newsService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url(""))
            .build()
            .create(NewsService::class.java)
    }

    private fun enqueueMock(fileName : String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }

    @Test
    fun getNewsHeadline_sentRequest_expectedResult() = runBlocking{
        enqueueMock("newsresponse.json")
        val responseBody = newsService.getNewsHeadlines("us", 1).body()
        val request = mockWebServer.takeRequest()
        Truth.assertThat(responseBody).isNotNull()
        Truth.assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=c0f3738e23a44631accec561a667379f")
    }

    @Test
    fun getNewsHeadlines_receivedResponse_correctSize() = runBlocking{
        enqueueMock("newsresponse.json")
        val responseBody = newsService.getNewsHeadlines("us", 1).body()
        val articleList = responseBody!!.articles!!
        Truth.assertThat(articleList.size).isEqualTo(20)
    }

    @Test
    fun getNewsHeadlines_receivedResponse_correctResult() = runBlocking{
        enqueueMock("newsresponse.json")
        val responseBody = newsService.getNewsHeadlines("us", 1).body()
        val articleList = responseBody!!.articles!!
        Truth.assertThat(articleList[0].author).isEqualTo("Annabelle Timsit, Bryan Pietsch, Miriam Berger")
        Truth.assertThat(articleList[0].title).isEqualTo("'Freedom Convoy' protesters, police face off at Canada's Ambassador Bridge - The Washington Post")
        Truth.assertThat(articleList[0].url).isEqualTo("https://www.washingtonpost.com/world/2022/02/13/canada-freedom-convoy-border-blockades-truckers/")
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}