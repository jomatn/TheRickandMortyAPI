package com.example.therickandmortyapi.ui.fragment.detail

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.therickandmortyapi.R
import com.example.therickandmortyapi.data.model.Result
import com.example.therickandmortyapi.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val binding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments?.getInt("characterId") ?: return
        viewModel.setCharacterId(characterId)

        viewModel.characterDetails.observe(viewLifecycleOwner) { character ->
            character?.let {
                bind(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                showToast(it)
            }
        }
    }

    private fun bind(character: Result) = with(binding) {
        tvName.text = character.name
        tvStatus.text = "${character.status} - ${character.species}"
        tvCurrentLocation.text = character.location.name
        tvCurrentGender.text = character.gender

        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            .format(Date())
        tvCurrentTime.text = currentTime

        Glide.with(root.context)
            .load(character.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.imgDetail)

        val circleDrawable = when (character.status) {
            "Alive" -> R.drawable.alive_dot
            "Dead" -> R.drawable.dead_dot
            else -> R.drawable.unknown_dot
        }
        imgCircle.setImageResource(circleDrawable)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}