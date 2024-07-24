package com.example.therickandmortyapi.ui.fragment.detail

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

    private val _characterDetails = MutableLiveData<Resource<Result>>()
    val characterDetails: LiveData<Resource<Result>> get() = _characterDetails

    private var characterId: Int? = null

    fun setCharacterId(id: Int) {
        characterId = id
        fetchCharacterDetails()
    }

    private fun fetchCharacterDetails() {
        characterId?.let { id ->
            viewModelScope.launch {
                repository.getCharacterById(id).observeForever { resource ->
                    _characterDetails.postValue(resource)
                }
            }
        }
    }
}
