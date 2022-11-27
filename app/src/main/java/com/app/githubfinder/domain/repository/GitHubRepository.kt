package com.app.githubfinder.domain.repository

import com.app.githubfinder.data.remote.dto.RepositoryModelDTO

interface GitHubRepository {
    suspend fun getAllRepositories(name: String): List<RepositoryModelDTO>
}