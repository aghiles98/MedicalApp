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
import android.widget.ListView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_connexion_medecin.*
import kotlinx.android.synthetic.main.fragment_recherche.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class connexionMedecin : Fragment() {
    fun verifyMed(num_tel:String,mdp:String) {
        val call = RetrofitService.endpoint.verifymedecin(num_tel,mdp)
        call.enqueue(object : Callback<List<Medecin>> {
            override fun onResponse(call: Call<List<Medecin>>?, response: Response<List<Medecin>>?) {
                if(response?.isSuccessful!!) {
                    if (response.body()?.size==0){
                        Toast.makeText(activity,"N° de télephone ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        var bundle = bundleOf("numero" to num_tel,"id_med" to response.body()?.get(0)?.id)
                        if (response.body()?.get(0)?.initial==1){
                            view?.findNavController()?.navigate(R.id.action_connexionMedecin_to_mdp_new, bundle)
                        }
                        else {
                            view?.findNavController()?.navigate(R.id.action_connexionMedecin_to_compte_medecin, bundle)
                        }
                    }
                    Log.e("erreur size success",response.body()?.size.toString())
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()

                }
                else{
                    Log.e("erreur erreur",response.body()?.size.toString())
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Medecin>>?, t: Throwable?) {
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
        return inflater.inflate(R.layout.fragment_connexion_medecin,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        patient = false
        val button5 = view.findViewById<Button>(R.id.button5)
        button5.setOnClickListener { view ->
            verifyMed(editText2.text.toString(),editText1.text.toString())
        }
    }

}