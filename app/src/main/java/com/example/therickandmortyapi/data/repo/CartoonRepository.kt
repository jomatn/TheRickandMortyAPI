package com.example.therickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.therickandmortyapi.data.api.CartoonApiService
import com.example.therickandmortyapi.data.model.Result
import com.example.therickandmortyapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CartoonRepository @Inject constructor(
    private val apiService: CartoonApiService
) {

    fun getAllCharacters(): LiveData<Resource<List<Result>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = apiService.getAllCharacters()
            emit(Resource.Success(response.results))
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching characters: ${e.message}"))
        }
    }

    fun getCharacterById(id: Int): LiveData<Resource<Result>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = apiService.getCharacterById(id)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching character: ${e.message}"))
        }
    }
}
