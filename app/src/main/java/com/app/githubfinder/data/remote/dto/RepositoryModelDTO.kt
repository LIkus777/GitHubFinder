package com.app.githubfinder.data.remote.dto

import com.app.githubfinder.domain.model.OwnerModel
import com.app.githubfinder.domain.model.RepositoriesModel
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryModelDTO(
    val name: String,
    val owner: OwnerModel,
    val description: String?,
    val language: String,
    val visibility: String,
    val created_at: String,
    val updated_at: String,
    val pushed_at: String,
    val watchers: Long
) {
    fun mapToReposModel(): RepositoriesModel {
        return RepositoriesModel(
            name = name,
            owner = owner,
            description = description,
            language = language,
            visibility = visibility,
            created_at = created_at,
            updated_at = updated_at,
            pushed_at = pushed_at,
            watchers = watchers
        )
    }
}

