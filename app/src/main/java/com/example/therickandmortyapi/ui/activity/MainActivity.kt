package com.example.therickandmortyapi.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.therickandmortyapi.R
import com.example.therickandmortyapi.ui.CharacterAdapter
import com.example.therickandmortyapi.ui.fragment.ListFragment
import com.example.therickandmortyapi.ui.viewmodel.CartoonViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListFragment())
                .commit()
        }
    }
}