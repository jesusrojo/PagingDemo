package com.jesusrojo.pagingdemo.ghrepos.presentation.hilt


import com.jesusrojo.pagingdemo.ghrepos.data.api.GHReposAPIService
import com.jesusrojo.pagingdemo.ghrepos.data.db.GHReposDAO
import com.jesusrojo.pagingdemo.ghrepos.data.repository.GHRepoRepositoryImpl
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposCacheDataSource
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposLocalDataSource
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasource.GHReposRemoteDataSource
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl.GHReposCacheDataSourceImpl
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl.GHReposLocalDataSourceImpl
import com.jesusrojo.pagingdemo.ghrepos.data.repository.datasourceimpl.GHReposRemoteDataSourceImpl
import com.jesusrojo.pagingdemo.ghrepos.data.util.MapperRawToEnty
import com.jesusrojo.pagingdemo.ghrepos.domain.repository.GHReposRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGHReposRepository(
        cacheDataSource: GHReposCacheDataSource,
        localDataSource: GHReposLocalDataSource,
        remoteDataSource: GHReposRemoteDataSource,
        mapper: MapperRawToEnty
    ): GHReposRepository {
        return GHRepoRepositoryImpl(
            remoteDataSource, localDataSource, cacheDataSource, mapper
        )
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(
        newsAPIService: GHReposAPIService
    ): GHReposRemoteDataSource {
        return GHReposRemoteDataSourceImpl(newsAPIService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(myDAO: GHReposDAO): GHReposLocalDataSource {
        return GHReposLocalDataSourceImpl(myDAO)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(): GHReposCacheDataSource {
        return GHReposCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideMapperRawToEnty(): MapperRawToEnty {
        return MapperRawToEnty()
    }
}