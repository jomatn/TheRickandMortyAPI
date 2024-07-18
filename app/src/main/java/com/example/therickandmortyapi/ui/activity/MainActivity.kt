package com.example.therickandmortyapi.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.therickandmortyapi.R
import com.example.therickandmortyapi.ui.CharacterAdapter
import com.example.therickandmortyapi.ui.viewmodel.CartoonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CartoonViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        characterAdapter = CharacterAdapter()
        recyclerView.adapter = characterAdapter

        viewModel.characters.observe(this) { characters ->
            if (characters != null) {
                characterAdapter.submitList(characters)
            } else {
                Log.e("MainActivity", "Failed to fetch characters")
            }
        }
    }
}
