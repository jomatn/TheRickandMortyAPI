package com.example.therickandmortyapi.ui.fragment.list

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.therickandmortyapi.R
import com.example.therickandmortyapi.data.model.Result
import com.example.therickandmortyapi.databinding.FragmentListBinding
import com.example.therickandmortyapi.ui.viewmodel.ListViewModel
import com.example.therickandmortyapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by viewModels()

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter { character ->
            navigateToDetailFragment(character.id)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }
    }

    private fun setupObservers() {
        viewModel.characterList.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    resource.data?.let { characters ->
                        characterAdapter.submitList(characters)
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(resource.message)
                }
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetailFragment(characterId: Int) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(characterId)
        action.let { it1 -> view?.let { it2 -> androidx.navigation.Navigation.findNavController(it2).navigate(it1) } }
    }
}
