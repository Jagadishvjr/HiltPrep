package com.jagadishvjr.hiltprep.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenProvider : TokenProvider
): Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenProvider.getToken()
        val newRequest = if(!token.isNullOrBlank()){
            originalRequest.newBuilder()
                .addHeader("Autherization", "Bearer $token")
                .build()
        }else{
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}