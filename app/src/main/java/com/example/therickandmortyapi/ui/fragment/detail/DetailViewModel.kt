package com.example.therickandmortyapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therickandmortyapi.data.model.Result
import com.example.therickandmortyapi.data.repository.CartoonRepository
import com.example.therickandmortyapi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _characterDetails = MutableLiveData<Result?>()
    val characterDetails: LiveData<Result?> = _characterDetails

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun setCharacterId(characterId: Int) {
        viewModelScope.launch {
            repository.getCharacterById(characterId).observeForever { resource ->
                when (resource) {
                    is Resource.Success -> _characterDetails.postValue(resource.data)
                    is Resource.Error -> _error.postValue(resource.message)
                    else -> Unit
                }
            }
        }
    }
}
