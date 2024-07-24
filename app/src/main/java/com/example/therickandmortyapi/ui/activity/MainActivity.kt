package com.example.therickandmortyapi.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.therickandmortyapi.R
import com.example.therickandmortyapi.ui.fragment.list.ListFragment
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
