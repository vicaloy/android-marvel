package com.example.marvel.ui.character.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marvel.databinding.CharacterRowItemBinding
import com.example.marvel.domain.model.Characters

class CharactersAdapter(private val onClickListener: (Characters) -> Unit) : PagingDataAdapter<Characters, CharactersViewHolder>(
    CHARACTER_DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            CharacterRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            onClickListener
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            holder.bind(character)
        }
    }

    companion object {
        val CHARACTER_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Characters>() {
            override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean =
                oldItem == newItem
        }
    }


}