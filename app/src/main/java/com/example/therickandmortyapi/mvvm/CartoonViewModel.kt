package com.example.therickandmortyapi.ui.viewmodel

import androidx.lifecycle.*
import com.example.therickandmortyapi.data.model.Result
import com.example.therickandmortyapi.mvvm.CartoonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartoonViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _characters = MutableLiveData<List<Result>>()
    val characters: LiveData<List<Result>> get() = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllCharacters()
                _characters.postValue(response.results)
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}
