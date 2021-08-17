package com.jesusrojo.pagingdemo.ghrepos.data.api


import com.google.common.truth.Truth.assertThat
import com.jesusrojo.pagingdemo.ghrepos.data.model.GHReposList
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

class GHReposAPIServiceTest {

    private lateinit var service: GHReposAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GHReposAPIService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(fileName:String){
      val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
      val source = inputStream.source().buffer()
      val mockResponse = MockResponse()
      mockResponse.setBody(source.readString(Charsets.UTF_8))
      server.enqueue(mockResponse)

    }

    private suspend fun prepareAPIResponse(): GHReposList {
        enqueueMockResponse("ghreposresponse.json")
        val responseBody = service.fetchGHRepos(1).body()
        return responseBody!!
    }

    @Test
    fun getGHRepos_sentRequest_receivedExpected(){
        runBlocking {
            val responseBody = prepareAPIResponse()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path)
                .isEqualTo("/search/repositories?q=language:kotlin&sort=stars&page=1")
        }
    }

    @Test
    fun getGHRepos_receivedResponse_correctPageSize(){
      runBlocking {
          val responseBody = prepareAPIResponse()
          val articlesList = responseBody.items
          assertThat(articlesList.size).isEqualTo(30)
      }
    }

    @Test
    fun getGHRepos_receivedResponse_correctContent(){
        runBlocking {
            val responseBody = prepareAPIResponse()
            val articlesList = responseBody.items
            val article = articlesList[0]
            assertThat(article.repoName).isEqualTo("architecture-samples")
            assertThat(article.description).isEqualTo("A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.")
            assertThat(article.createdAt).isEqualTo("2016-02-05T13:42:07Z")
        }
    }
}