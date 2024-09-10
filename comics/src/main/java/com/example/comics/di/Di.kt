package com.example.comics.di

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.comics.domain.action.FindComic
import com.example.comics.domain.action.GetComics
import com.example.comics.domain.repository.ComicsRepository
import com.example.comics.infra.client.ComicsClient
import com.example.comics.infra.paging.ComicsPagingSource
import com.example.comics.ui.ComicsViewModelFactory
import com.example.common.di.Di
import com.example.marvel.infra.repository.ComicsRemoteRepository

object Di {

    fun provideComicsViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ComicsViewModelFactory(owner, provideComicsPagingSource())
    }

    private fun provideGetComics() = GetComics(provideComicsRepository())

    private fun provideFindCharacter() = FindComic(provideComicsRepository())

    private fun provideComicsRepository(): ComicsRepository = ComicsRemoteRepository(
        provideComicsClient()
    )

    private fun provideComicsPagingSource() = ComicsPagingSource(provideGetComics())

    private fun provideComicsClient(): ComicsClient {
        return Di.retrofit
            .create(ComicsClient::class.java)
    }
}