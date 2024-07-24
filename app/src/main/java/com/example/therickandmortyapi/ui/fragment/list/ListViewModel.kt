package com.example.therickandmortyapi.ui.fragment.list

import androidx.lifecycle.*
import com.example.therickandmortyapi.data.model.Result
import com.example.therickandmortyapi.data.repository.CartoonRepository
import com.example.therickandmortyapi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    val characters: LiveData<Resource<List<Result>>> = repository.getAllCharacters()
}
