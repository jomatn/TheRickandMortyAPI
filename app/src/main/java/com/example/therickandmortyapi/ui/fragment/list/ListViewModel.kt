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
class ListViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _characterList = MutableLiveData<Resource<List<Result>>>()
    val characterList: LiveData<Resource<List<Result>>> = _characterList

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() = viewModelScope.launch {
        repository.getAllCharacters().observeForever { _characterList.postValue(it) }
    }
}
