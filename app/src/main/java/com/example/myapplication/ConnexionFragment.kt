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
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_connexion.*
import kotlinx.android.synthetic.main.fragment_connexion_medecin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConnexionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    fun verifyPat(num_tel:String,mdp:String) {
        val call = RetrofitService.endpoint.verifypatient(num_tel,mdp)
        call.enqueue(object : Callback<List<Patient>> {
            override fun onResponse(call: Call<List<Patient>>?, response: Response<List<Patient>>?) {
                if(response?.isSuccessful!!) {
                    if (response.body()?.size==0){
                        Toast.makeText(activity,"N° de télephone ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        var bundle = bundleOf("numero" to num_tel)
                        var bundle1 = bundleOf("nss" to response.body()?.get(0)?.nss)
                        if(response.body()?.get(0)?.initial==1){
                            view?.findNavController()?.navigate(R.id.action_connexionFragment_to_mdp_new, bundle)
                        }
                        else{
                            view?.findNavController()?.navigate(R.id.action_connexionFragment_to_compte_patient, bundle1)
                        }
                    }
                    Log.e("nss",response.body()?.get(0)?.nss)
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()

                }
                else{
                    Log.e("erreur erreur",response.body()?.size.toString())
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Patient>>?, t: Throwable?) {
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
        return inflater.inflate(R.layout.fragment_connexion,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        patient = true
        val button4 = view.findViewById<Button>(R.id.button4)
        button4.setOnClickListener { view ->
            verifyPat(editText.text.toString(),editText3.text.toString())
        }

    }

}