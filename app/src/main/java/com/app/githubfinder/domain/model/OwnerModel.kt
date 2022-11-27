package com.app.githubfinder.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class OwnerModel(
    val html_url: String,
)