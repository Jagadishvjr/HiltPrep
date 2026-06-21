package com.jagadishvjr.hiltprep.data.remote

interface TokenProvider {
    fun getToken(): String?
}