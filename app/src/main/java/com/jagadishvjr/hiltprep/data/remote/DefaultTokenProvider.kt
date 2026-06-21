package com.jagadishvjr.hiltprep.data.remote

import javax.inject.Inject

class DefaultTokenProvider @Inject constructor() : TokenProvider{
    override fun getToken(): String? {
        return null
    }
}