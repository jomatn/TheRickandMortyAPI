package com.example.therickandmortyapi.data.api

import com.example.therickandmortyapi.data.model.BaseResponse
import retrofit2.http.GET

interface CartoonApiService {
    @GET("character")
    suspend fun getAllCharacters(): BaseResponse
}
