package com.example.marvel.ui.character.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.marvel.di.Di
import com.example.common.ui.Screen

class CharacterDetailFragment : Fragment() {

    private val viewModel by viewModels<CharacterDetailViewModel>(
        factoryProducer = { Di.provideCharacterDetailViewModelFactory(owner = this) }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments?.getLong("characterId")
        if (characterId != null) {
            viewModel.getCharacter(characterId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext()).apply {
            setContent {
                Screen {
                    CharacterDetailScreen(viewModel)
                }
            }
        }
        return composeView
    }
}