package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_inscription_patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class inscription_patient : Fragment() {
    private fun generateRandomPassword(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..9) {
            passWord += chars[Math.floor(Math.random() * chars.length).toInt()]
        }
        return passWord
    }
    private fun addPatient(p: Patient) {
        val call = RetrofitService.endpoint.addpatient(p)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(getActivity(),"INSCRIPTION REUSSIE",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("erreur retrofit",t.toString())
                // Un toast pour l'utilisateur
                Toast.makeText(getActivity(),"ECHEC INSCRIPTION",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inscription_patient,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button6 = view.findViewById<Button>(R.id.button6)
        actif = false
        patient = true
        button6.setOnClickListener { view ->
            var nss = editText5.text.toString()
            var nom = editText6.text.toString()
            var prenom = editText7.text.toString()
            var num_tel = editText8.text.toString()
            var adresse = editText9.text.toString()
            var mdp = generateRandomPassword()
            var p = Patient(nss,nom,prenom,adresse,num_tel,mdp,1,0)
            addPatient(p)
            view.findNavController().navigate(R.id.action_inscription_patient_to_connexionFragment)
        }

    }

}
