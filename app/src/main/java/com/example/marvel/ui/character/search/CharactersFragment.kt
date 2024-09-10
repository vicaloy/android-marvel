package com.example.marvel.ui.character.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.databinding.FragmentCharacterBinding
import com.example.marvel.di.Di
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<CharactersViewModel>(
        factoryProducer = { Di.provideCharacterViewModelFactory(owner = this) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCharactersRecycler()
        bindSearchButton()
        showCharacters()
        showProgress()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showCharacters() {
        val adapter = (binding.charactersRecycler.adapter as CharactersAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->

                    binding.queryTextview.text = uiState.query

                    uiState.characters.let { characters ->
                        characters.collectLatest { character ->
                            adapter.submitData(character)
                        }
                    }

                    if (uiState.hasError)
                        showErrorAlert()
                }
            }
        }
    }

    private fun showProgress() {
        val adapter = (binding.charactersRecycler.adapter as CharactersAdapter)
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> binding.loading.visibility = View.VISIBLE

                    is LoadState.Error -> showErrorAlert()

                    is LoadState.NotLoading -> binding.loading.visibility = View.GONE
                }
            }
        }
    }

    private fun showErrorAlert() {
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.alert_title))
            builder.setMessage(getString(R.string.alert_message))

            builder.setPositiveButton(getString(R.string.alert_button_retry)) { _, _ ->
                val query = binding.queryEdittext.text.toString()
                viewModel.fetchCharacters(query)
            }

            builder.setNegativeButton(getString(R.string.alert_button_cancel)) { _, _ ->
                binding.loading.visibility = View.GONE
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    private fun bindCharactersRecycler() {
        with(binding) {
            charactersRecycler.adapter = CharactersAdapter { character ->
                onNavigateCharacterDetail(character.id)
            }
            charactersRecycler.layoutManager = LinearLayoutManager(charactersRecycler.context)
            val decoration =
                DividerItemDecoration(charactersRecycler.context, DividerItemDecoration.VERTICAL)
            charactersRecycler.addItemDecoration(decoration)
        }
    }

    private fun bindSearchButton() {
        binding.searchButton.setOnClickListener {
            val query = binding.queryEdittext.text.toString()
            viewModel.fetchCharacters(query)
        }
    }

    private fun onNavigateCharacterDetail(characterId: Long) {
        val request = NavDeepLinkRequest.Builder
            .fromUri("marvel://characterDetail/$characterId".toUri())
            .build()
        findNavController().navigate(request)
    }
}