package com.example.myapplication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_mdp_new.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class mdp_new : Fragment() {

    fun updateMed(num_tel:String,mdp:String) {
        val call = RetrofitService.endpoint.updatemedecin(num_tel,mdp)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(activity,"Mot de passe changé", Toast.LENGTH_SHORT).show()
                    Log.e("erreur size success","dfe")
                    view?.findNavController()?.navigate(R.id.action_mdp_new_to_connexionMedecin)
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()

                }
                else{
                    Log.e("erreur erreur","kjl")
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
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
    fun updatePat(num_tel:String,mdp:String) {
        val call = RetrofitService.endpoint.updatepatient(num_tel,mdp)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(activity,"Mot de passe changé", Toast.LENGTH_SHORT).show()
                    Log.e("erreur size success","dfe")
                    view?.findNavController()?.navigate(R.id.action_mdp_new_to_connexionFragment)
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()

                }
                else{
                    Log.e("erreur erreur","kjl")
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mdp_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button22 = view.findViewById<Button>(R.id.button22)
        actif = false
        button22.setOnClickListener { view ->
            if (editText22.text.toString().equals(editText23.text.toString())){
                val num = arguments?.getString("numero")
                if (patient){
                    updatePat(num!!,editText22.text.toString())
                }
                else{
                    updateMed(num!!,editText22.text.toString())
                }
            }
            else{
                Toast.makeText(activity,"les mots de passe ne sont pas egaux", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
