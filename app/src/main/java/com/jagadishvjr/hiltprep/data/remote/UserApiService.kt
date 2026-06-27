package com.jagadishvjr.hiltprep.data.remote

import com.jagadishvjr.hiltprep.data.remote.dto.UserDto
import retrofit2.http.GET

interface UserApiService {

    @GET("usersvjr")
    suspend fun getUsers(): List<UserDto>
}