package com.example.therickandmortyapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.therickandmortyapi.databinding.FragmentListBinding
import com.example.therickandmortyapi.ui.CharacterAdapter
import com.example.therickandmortyapi.ui.viewmodel.CartoonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[CartoonViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            binding.recyclerView.adapter = CharacterAdapter(characters)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
