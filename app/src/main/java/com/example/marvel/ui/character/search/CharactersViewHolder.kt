package com.example.marvel.ui.character.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.databinding.CharacterRowItemBinding
import com.example.comics.domain.model.Comics
import com.example.marvel.domain.model.Characters

class CharactersViewHolder(
    private val binding: CharacterRowItemBinding,
    private val onClickListener: (Characters) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Characters) {
        binding.apply {
            root.setOnClickListener { onClickListener(character) }
            titleItem.text = character.name
            Glide.with(imageItem.context)
                .load(character.thumbnail.toString())
                .into(imageItem)
        }
    }
}