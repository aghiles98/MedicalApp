package com.example.myapplication


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_prisemed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class prisemed : Fragment() {

    private fun addLignetrt(l: Ligne_trt) {
        val call = RetrofitService.endpoint.addlignetrt(l)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(getActivity(),"Ajout REUSSI", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("erreur retrofit",t.toString())
                // Un toast pour l'utilisateur
                Toast.makeText(getActivity(),"ECHEC AJOUT", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    fun getMdcmid(designation:String?) {
        val call = RetrofitService.endpoint.getmdcm(designation!!)
        call.enqueue(object : Callback<List<Medicament>> {
            override fun onResponse(call: Call<List<Medicament>>?, response: Response<List<Medicament>>?) {
                if(response?.isSuccessful!!) {
                    Log.e("erreur size success",response.body()?.size.toString())
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()
                    var id_trt = arguments?.getInt("id_trt")
                    var l = Ligne_trt(null,editheureprs.text.toString(),id_trt!!,response.body()?.get(0)?.id)
                    addLignetrt(l)

                }
                else{
                    Log.e("erreur erreur",response.body()?.size.toString())
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Medicament>>?, t: Throwable?) {
                Log.e("erreur retrofit",t.toString())
                // Un toast pour l'utilisateur
                Toast.makeText(activity,"ECHEC INSCRIPTION", Toast.LENGTH_SHORT).show()
            }
        }
        )
    }

    private fun addMdcm(m:Medicament) {
        val call = RetrofitService.endpoint.addmedicament(m)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(getActivity(),"Ajout REUSSI", Toast.LENGTH_SHORT).show()
                    getMdcmid(m.designation)
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("erreur retrofit",t.toString())
                // Un toast pour l'utilisateur
                Toast.makeText(getActivity(),"ECHEC AJOUT", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prisemed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var m = Medicament(null,editmeddes.text.toString())
        addMdcm(m)

    }


}
