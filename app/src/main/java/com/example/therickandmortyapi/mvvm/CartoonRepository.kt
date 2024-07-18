package com.example.therickandmortyapi.mvvm

import com.example.therickandmortyapi.data.api.CartoonApiService
import javax.inject.Inject

class CartoonRepository @Inject constructor(
    private val apiService: CartoonApiService) {

    suspend fun getAllCharacters() = apiService.getAllCharacters()
}