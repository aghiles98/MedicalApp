package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_type_compte.*


class type_compte : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_type_compte,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actif = false
        val button9 = view.findViewById<Button>(R.id.button9)
        val button8 = view.findViewById<Button>(R.id.button8)
        button9.setOnClickListener { view ->
            patient = false
            if (connex){
                view.findNavController().navigate(R.id.action_type_compte_to_connexionMedecin2)
            }
            else{
                view.findNavController().navigate(R.id.action_type_compte_to_inscription_medecin)
            }
        }
        button8.setOnClickListener { view ->
            patient = true
            if (connex){
                view.findNavController().navigate(R.id.action_type_compte_to_connexionFragment)
            }
            else {
                view.findNavController().navigate(R.id.action_type_compte_to_inscription_patient)
            }
        }
    }

}
