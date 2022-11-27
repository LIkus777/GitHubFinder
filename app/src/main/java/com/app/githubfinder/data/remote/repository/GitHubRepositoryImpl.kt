package com.app.githubfinder.data.remote.repository

import com.app.githubfinder.data.remote.GitHubApi
import com.app.githubfinder.data.remote.dto.RepositoryModelDTO
import com.app.githubfinder.domain.repository.GitHubRepository

class GitHubRepositoryImpl(
    private val api: GitHubApi
): GitHubRepository {
    override suspend fun getAllRepositories(name: String): List<RepositoryModelDTO> {
        return api.getAllRepositories(name = name)
    }
}