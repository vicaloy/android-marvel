package com.example.marvel.ui.character.search

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CharactersDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Characters dialog")
            .setPositiveButton("Ok") { _,_ -> }
            .create()

    companion object {
        const val TAG = "CharactersDialogFragment"
    }
}