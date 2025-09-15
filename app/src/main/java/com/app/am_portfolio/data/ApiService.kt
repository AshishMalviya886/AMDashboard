package com.app.am_portfolio.data

import retrofit2.http.GET


interface ApiService {
    @GET("posts")              // baseUrl should be https://dummyjson.com/
    suspend fun getPosts(): PostsResponse
}

