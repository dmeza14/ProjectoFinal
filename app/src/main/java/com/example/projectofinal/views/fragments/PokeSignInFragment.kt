package com.example.projectofinal.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.projectofinal.R
import com.example.projectofinal.views.activities.DetailedActivity

class PokeSignInFragment: Fragment(R.layout.fragment_poke_sign_in) {
    private lateinit var signInButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton = view.findViewById(R.id.signInButton)

        signInButton.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, DetailedActivity::class.java))
            }
        }
    }
}