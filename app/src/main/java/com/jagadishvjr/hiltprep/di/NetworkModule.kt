package com.jagadishvjr.hiltprep.di

import com.jagadishvjr.hiltprep.data.remote.ApiService
import com.jagadishvjr.hiltprep.data.remote.AuthInterceptor
import com.jagadishvjr.hiltprep.data.remote.DefaultTokenProvider
import com.jagadishvjr.hiltprep.data.remote.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun providesTokenProvider(): TokenProvider = DefaultTokenProvider()

    @Provides
    @Singleton
    fun providesAuthInterceptor(
        tokenProvider: TokenProvider
    ): AuthInterceptor{
        return AuthInterceptor(tokenProvider)
    }

    @Provides
    @Singleton
    fun providesLoginInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(
        okhttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesApiService(
        retrofit: Retrofit
    ): ApiService{
        return retrofit.create(ApiService::class.java)
    }



}