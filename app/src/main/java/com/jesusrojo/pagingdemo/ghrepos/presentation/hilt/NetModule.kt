package com.jesusrojo.pagingdemo.ghrepos.presentation.hilt


import com.jakewharton.espresso.OkHttp3IdlingResource
import com.jesusrojo.pagingdemo.ghrepos.data.api.GHReposAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// RETROFIT
private val okHttp2Client: OkHttpClient.Builder = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

private val client = okHttp2Client.build()

// TESTING
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)



@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(GHReposAPIService.BASE_URL)
             .client(client)
             .build()
    }

    @Singleton
    @Provides
    fun provideGHReposAPIService(retrofit: Retrofit): GHReposAPIService {
        return retrofit.create(GHReposAPIService::class.java)
    }
}