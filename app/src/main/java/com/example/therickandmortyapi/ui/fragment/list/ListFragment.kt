package com.example.therickandmortyapi.ui.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.therickandmortyapi.databinding.FragmentListBinding
import com.example.therickandmortyapi.data.model.Result
import com.example.therickandmortyapi.utils.Resource
import com.example.therickandmortyapi.utils.gone
import com.example.therickandmortyapi.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by viewModels()

    private val characterAdapter by lazy {
        CharacterAdapter { result -> onClicked(result) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = characterAdapter
    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showProgressBar(true)
                is Resource.Success -> {
                    showProgressBar(false)
                    characterAdapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    showProgressBar(false)
                    showToast(resource.message ?: "An unexpected error occurred.")
                }
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) = with(binding) {
        if (isVisible) progressBar.visible() else progressBar.gone()
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun onClicked(result: Result) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(result.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
