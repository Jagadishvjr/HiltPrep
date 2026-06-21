package com.jagadishvjr.hiltprep.data.remote

import com.jagadishvjr.hiltprep.data.remote.dto.UserDto
import retrofit2.http.GET

interface ApiService{

    @GET("users")
    suspend fun gerUsers(): List<UserDto>
}