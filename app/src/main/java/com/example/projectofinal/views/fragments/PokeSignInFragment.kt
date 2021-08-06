package com.example.projectofinal.views.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.projectofinal.R
import com.example.projectofinal.views.activities.DetailedActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class PokeSignInFragment: Fragment(R.layout.fragment_poke_sign_in) {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var signInButton: Button
    private lateinit var entrenadorEditText: TextInputEditText
    private lateinit var entrenadorLayout: TextInputLayout
    private lateinit var masculinoRadioButton: RadioButton
    private lateinit var femeninoRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton = view.findViewById(R.id.signInButton)
        entrenadorEditText = view.findViewById(R.id.entrenadorEditText)
        masculinoRadioButton = view.findViewById(R.id.masculinoRadioButton)
        femeninoRadioButton = view.findViewById(R.id.femeninoRadioButton)
        entrenadorLayout = view.findViewById(R.id.entrenadorTextView)

        //NEED TO WORK ON VALIDATIONS!!!
        signInButton.setOnClickListener{
            if ((!masculinoRadioButton.isChecked && !femeninoRadioButton.isChecked) || entrenadorEditText.text.toString().isEmpty()) {
                Snackbar.make(signInButton, "Campos requeridos faltantes", Snackbar.LENGTH_LONG).show()
            }else{
                sharedPref.edit {
                    putString("ENTRENADOR_NAME", entrenadorEditText.text.toString())
                    putBoolean("MASCULINO_CHECKED",masculinoRadioButton.isChecked)
                    putBoolean("FEMENINO_CHECKED",femeninoRadioButton.isChecked)
                    apply()
                    requireActivity().run {
                        startActivity(Intent(this, DetailedActivity::class.java))
                    }
                }

            }

        }
    }
}