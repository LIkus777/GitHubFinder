package com.app.githubfinder.presentation.repository_list

import com.app.githubfinder.domain.model.RepositoriesModel

data class RepositoriesState(
    val listOfRepositories: List<RepositoriesModel> = emptyList(),
    val error: String = ""
)