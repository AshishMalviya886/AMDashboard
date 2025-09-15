package com.app.am_portfolio.data

import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val posts: List<Post>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String> = emptyList(),
    val reactions: Reactions? = null,
    val views: Int? = null,
    val userId: Int? = null
)

@Serializable
data class Reactions(
    val likes: Int = 0,
    val dislikes: Int = 0
)

