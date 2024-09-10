package com.example.marvel.di

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.common.di.Di as CommonDi
import com.example.marvel.domain.action.FindCharacter
import com.example.marvel.domain.action.GetCharacters
import com.example.marvel.domain.repository.CharactersRepository
import com.example.marvel.infra.client.CharactersClient
import com.example.marvel.infra.paging.CharactersPagingSource
import com.example.marvel.infra.repository.CharactersRemoteRepository
import com.example.marvel.ui.character.detail.CharacterDetailViewModelFactory
import com.example.marvel.ui.character.search.CharactersViewModelFactory

object Di {

    fun provideCharacterViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return CharactersViewModelFactory(owner, provideCharactersPagingSource())
    }

    fun provideCharacterDetailViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return CharacterDetailViewModelFactory(owner, provideFindCharacter())
    }

    private fun provideGetCharacters() = GetCharacters(provideCharactersRepository())

    private fun provideFindCharacter() = FindCharacter(provideCharactersRepository())

    private fun provideCharactersRepository(): CharactersRepository = CharactersRemoteRepository(
        provideCharactersClient()
    )

    private fun provideCharactersPagingSource() = CharactersPagingSource(provideGetCharacters())

    private fun provideCharactersClient(): CharactersClient {
        return CommonDi.retrofit
            .create(CharactersClient::class.java)
    }
}