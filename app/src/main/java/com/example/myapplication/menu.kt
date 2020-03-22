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
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_inscription_patient.*
import kotlinx.android.synthetic.main.fragment_menu.*


class menu : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actif = false
        val button = view?.findViewById<Button>(R.id.button)
        val button2 = view?.findViewById<Button>(R.id.button2)
        val button3 = view?.findViewById<Button>(R.id.button3)
        button?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_menu_to_type_compte)
            connex = true
        }
        button2?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_menu_to_type_compte)
            connex = false
        }
        button3?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_menu_to_recherche)
            actif = false
        }

    }

}
