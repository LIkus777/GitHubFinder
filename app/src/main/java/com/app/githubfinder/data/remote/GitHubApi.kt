package com.app.githubfinder.data.remote

import com.app.githubfinder.data.remote.dto.RepositoryModelDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{name}/repos")
    suspend fun getAllRepositories(
        @Path("name") name: String
    ): List<RepositoryModelDTO>
}