package com.jesusrojo.pagingdemo.paging.ui.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.jesusrojo.pagingdemo.BuildConfig
import com.jesusrojo.pagingdemo.paging.data.local.db.RepoDatabaseService
import com.jesusrojo.pagingdemo.paging.data.remote.GithubApiService
import com.jesusrojo.pagingdemo.paging.data.repository.mediator.ReposRemoteMediator
import com.jesusrojo.pagingdemo.paging.data.repository.mediator.ReposRemoteMediatorRepository
import com.jesusrojo.pagingdemo.paging.data.repository.mediator.ReposRemoteMediatorRepositoryImpl
import com.jesusrojo.pagingdemo.paging.data.repository.pagingsource.ReposPagingSource
import com.jesusrojo.pagingdemo.paging.data.repository.pagingsource.ReposRepositoryImpl
import com.jesusrojo.pagingdemo.paging.ui.viewmodel.ReposRemoteMediatorViewModel
import com.jesusrojo.pagingdemo.paging.ui.viewmodel.ReposViewModel
import com.jesusrojo.pagingdemo.utils.ViewModelProviderFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class Injection {

    companion object {
        fun getReposViewModel(
            activity: AppCompatActivity
        ): ReposViewModel =
            ViewModelProvider(activity,
                ViewModelProviderFactory(ReposViewModel::class) {
                    ReposViewModel(getReposRepository(activity.cacheDir))
                }).get(ReposViewModel::class.java)

        private fun getReposRepository(cacheDir: File): ReposRepositoryImpl {
            return ReposRepositoryImpl(getReposPagingSource(cacheDir))
        }

        private fun getReposPagingSource(cacheDir: File): ReposPagingSource =
            ReposPagingSource(getGithubApiService(cacheDir))


        private fun getGithubApiService(cacheDir: File): GithubApiService {
            return getRetrofitGithub(cacheDir)
        }

        private fun getReposDataService(applicationContext: Context): RepoDatabaseService =
            RepoDatabaseService.getInstance(applicationContext)


        // MEDIATOR
        @ExperimentalPagingApi
        fun getReposRemoteMediatorViewModel(activity: AppCompatActivity): ReposRemoteMediatorViewModel {
            return ViewModelProvider(activity,
                ViewModelProviderFactory(ReposRemoteMediatorViewModel::class) {
                    ReposRemoteMediatorViewModel(getMediatorRepository(activity))
                }).get(ReposRemoteMediatorViewModel::class.java)
        }

        @ExperimentalPagingApi
        private fun getMediatorRepository(activity: AppCompatActivity): ReposRemoteMediatorRepository {
            val databaseService = getReposDataService(activity.applicationContext)
            return ReposRemoteMediatorRepositoryImpl(
                databaseService,
                getRemoteMediator(activity.cacheDir, databaseService)
            )
        }

        @ExperimentalPagingApi
        fun getRemoteMediator(
            cacheDir: File, databaseService: RepoDatabaseService
        ): ReposRemoteMediator =
            ReposRemoteMediator(getGithubApiService(cacheDir), databaseService)


        private fun getRetrofitGithub(cacheDir: File): GithubApiService {
            return getRetrofitBuilder(
                cacheDir,
                GithubApiService.BASE_URL_GITHUB
            ).create(GithubApiService::class.java)
        }

        // RETROFIT
        private const val NETWORK_CALL_TIME_OUT = 60L
        private const val CACHE_SIZE: Long = 1024 * 1024 * 10

        private fun getRetrofitBuilder(cacheDir: File, baseUrl: String): Retrofit {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .cache(Cache(cacheDir, CACHE_SIZE))
                .readTimeout(NETWORK_CALL_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_CALL_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }).build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        //END RETROFIT
    }
}