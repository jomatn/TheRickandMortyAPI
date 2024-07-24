package com.example.therickandmortyapi.data.api

import com.example.therickandmortyapi.data.model.BaseResponse
import com.example.therickandmortyapi.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {

    @GET("character")
    suspend fun getAllCharacters(): BaseResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Result

}
