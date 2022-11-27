package com.app.githubfinder.di

import com.app.githubfinder.data.common.Constants
import com.app.githubfinder.data.remote.GitHubApi
import com.app.githubfinder.domain.repository.GitHubRepository
import com.app.githubfinder.data.remote.repository.GitHubRepositoryImpl
import com.app.githubfinder.domain.use_case.GetRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideGitHubApi(): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(api: GitHubApi): GitHubRepository {
        return GitHubRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetRepositoriesUseCase(gitHubRepository: GitHubRepository): GetRepositoriesUseCase {
        return GetRepositoriesUseCase(gitHubRepository)
    }
}