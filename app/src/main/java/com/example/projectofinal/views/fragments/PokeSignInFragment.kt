package com.example.projectofinal.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.projectofinal.R

class PokeSignInFragment: Fragment(R.layout.fragment_poke_sign_in) {
    private lateinit var signInButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton = view.findViewById(R.id.signInButton)

        signInButton.setOnClickListener{

            val action = PokeSignInFragmentDirections.actionPokeSignInFragmentToDetailedActivity()
            findNavController().navigate(action)
        }
    }
}