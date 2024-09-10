package com.example.comics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.comics.di.Di
import com.example.common.ui.Screen

class ComicsFragment : Fragment() {

    private val viewModel by viewModels<ComicsViewModel>(
        factoryProducer = { Di.provideComicsViewModelFactory(owner = this) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext()).apply {
            setContent {
                Screen {
                    ComicsScreen(viewModel)
                }
            }
        }
        return composeView
    }
}