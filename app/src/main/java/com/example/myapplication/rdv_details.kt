package com.example.myapplication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_rdv_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class rdv_details : Fragment() {

    private fun addRdv(d: Rdv) {
        val call = RetrofitService.endpoint.addrdv(d)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(activity,"AJOUT REUSSI", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("erreur retrofit",t.toString())
                // Un toast pour l'utilisateur
                Toast.makeText(activity,"ECHEC AJOUT", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rdv_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_rdv = view.findViewById<Button>(R.id.btn_rdv)
        btn_rdv.setOnClickListener {
            var d = Rdv(null,date_rdv.text.toString(),heure_rdv.text.toString(),arguments?.getString("nss_pt"),arguments?.getInt("id_med"))
            addRdv(d)
        }
    }


}
