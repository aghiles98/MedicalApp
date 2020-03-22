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
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_inscription_medecin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class inscription_medecin : Fragment() {
    fun generateRandomPassword(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..9) {
            passWord += chars[Math.floor(Math.random() * chars.length).toInt()]
        }
        return passWord
    }
    private fun addMedecin(m: Medecin) {
        val call = RetrofitService.endpoint.addmedecin(m)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(activity,"INSCRIPTION REUSSIE", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("erreur retrofit",t.toString())
                // Un toast pour l'utilisateur
                Toast.makeText(activity,"ECHEC INSCRIPTION", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inscription_medecin,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button7 = view.findViewById<Button>(R.id.button7)
        actif = false
        patient = false
        button7.setOnClickListener { view ->
            var gps = EditText10.text.toString()
            var nom = EditText11.text.toString()
            var prenom = EditText12.text.toString()
            var num_tel = EditText13.text.toString()
            var commune = EditText14.text.toString()
            var ouverture = editText15.text.toString()
            var fermeture = editText16.text.toString()
            var specialite = EditText17.text.toString()
            var adresse = EditText18.text.toString()
            var mdp = generateRandomPassword()
            var m = Medecin(null,nom,prenom,adresse,commune,specialite,ouverture,fermeture,num_tel,gps,mdp,1,0)
            addMedecin(m)
            view.findNavController().navigate(R.id.action_inscription_medecin_to_connexionMedecin)
        }

    }

}
