package com.app.githubfinder.domain.use_case

import com.app.githubfinder.data.common.Resource
import com.app.githubfinder.domain.model.RepositoriesModel
import com.app.githubfinder.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository
) {
    operator fun invoke(name: String): Flow<Resource<List<RepositoriesModel>>> = flow {
        try {
            val repository = gitHubRepository.getAllRepositories(name = name).map {
                it.mapToReposModel()
            }
            emit(Resource.Success(repository))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }
}