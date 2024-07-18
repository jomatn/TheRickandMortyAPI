package com.example.therickandmortyapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.therickandmortyapi.R
import com.example.therickandmortyapi.databinding.ItemCharacterBinding
import com.example.therickandmortyapi.data.model.Result

class CharacterAdapter(private var characterList: List<Result> = listOf())
    : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    fun submitList(characters: List<Result>) {
        this.characterList = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int = characterList.size

    class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Result) {
            binding.tvName.text = character.name
            binding.tvStatus.text = character.status
            binding.tvOther.text = character.species
            binding.tvLocation.text = character.location.name

            Glide.with(binding.root.context)
                .load(character.image)
                .into(binding.img)

            val statusDot = when (character.status) {
                "Alive" -> R.drawable.alive_dot
                "Dead" -> R.drawable.dead_dot
                else -> R.drawable.unknown_dot
            }
            binding.imgStatus.setImageResource(statusDot)
        }
    }
}
