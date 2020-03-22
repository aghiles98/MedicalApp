package com.example.myapplication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 */
class compte_patient : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compte_patient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle = bundleOf("nss" to arguments?.getString("nss"))
        connex = true
        actif = true
        val button31 = view.findViewById<Button>(R.id.button31)
        val button28 = view.findViewById<Button>(R.id.button28)
        val button29 = view.findViewById<Button>(R.id.button29)
        button31.setOnClickListener {
            connex=false
            view?.findNavController()?.navigate(R.id.action_compte_patient_to_connexionFragment)
        }
        button28.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_compte_patient_to_rdvpat,bundle)
        }
        button29.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_compte_patient_to_recherche,bundle)
        }
    }


}
