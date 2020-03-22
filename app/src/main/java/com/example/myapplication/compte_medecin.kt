package com.example.myapplication


import android.os.Bundle
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
class compte_medecin : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compte_medecin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connex=true
        actif = true
        var bundle = bundleOf("id_med" to arguments?.getInt("id_med"))
        val button23 = view.findViewById<Button>(R.id.button23)
        val button24 = view.findViewById<Button>(R.id.button24)
        val button25 = view.findViewById<Button>(R.id.button25)
        val button26 = view.findViewById<Button>(R.id.button26)
        button25.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_compte_medecin_to_listepat,bundle)
        }
        button26.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_compte_medecin_to_connexionMedecin)
        }
    }

}
