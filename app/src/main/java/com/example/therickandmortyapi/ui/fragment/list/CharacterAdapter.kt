package com.example.therickandmortyapi.ui.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.therickandmortyapi.R
import com.example.therickandmortyapi.databinding.ItemCharacterBinding
import com.example.therickandmortyapi.data.model.Result

class CharacterAdapter(
    private val onClick: (Result) -> Unit
) : ListAdapter<Result, CharacterAdapter.CharacterViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.tvName.text = result.name
            binding.tvStatus.text = result.status
            binding.tvOther.text = result.species
            binding.tvLocation.text = result.location.name

            Glide.with(binding.root.context)
                .load(result.image)
                .into(binding.img)

            val statusDot = when (result.status) {
                "Alive" -> R.drawable.alive_dot
                "Dead" -> R.drawable.dead_dot
                else -> R.drawable.unknown_dot
            }
            binding.imgStatus.setImageResource(statusDot)

            binding.root.setOnClickListener {
                onClick(result)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Result, newItem: Result) = oldItem == newItem
        }
    }
}
