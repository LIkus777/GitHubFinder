package com.app.githubfinder.domain.model

data class RepositoriesModel(
    val name: String,
    val owner: OwnerModel,
    val description: String?,
    val language: String,
    val visibility: String,
    val created_at: String,
    val updated_at: String,
    val pushed_at: String,
    val watchers: Long
)
