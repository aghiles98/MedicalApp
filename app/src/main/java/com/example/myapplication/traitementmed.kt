package com.example.myapplication


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
import kotlinx.android.synthetic.main.fragment_listepat.*
import kotlinx.android.synthetic.main.fragment_traitementmed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

/**
 * A simple [Fragment] subclass.
 */
class traitementmed : Fragment() {

    private fun addTrt(t: Traitement) {
        val call = RetrofitService.endpoint.addtrt(t)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response?.isSuccessful!!) {
                    Toast.makeText(getActivity(),"INSCRIPTION REUSSIE", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("erreur retrofit",t.toString())
                // Un toast pour l'utilisateur
                Toast.makeText(getActivity(),"ECHEC INSCRIPTION", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    fun getTrtid(nss:String?, id_med:Int?) {
        val call = RetrofitService.endpoint.gettrtid(nss!!, id_med!!)
        call.enqueue(object : Callback<List<Traitement>> {
            override fun onResponse(call: Call<List<Traitement>>?, response: Response<List<Traitement>>?) {
                if(response?.isSuccessful!!) {
                    Log.e("erreur size success",response.body()?.size.toString())
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()
                    val bundle = bundleOf("id_trt" to response.body()?.get(0)?.id)
                    view?.findNavController()?.navigate(R.id.action_traitementmed_to_prisemed,bundle)

                }
                else{
                    Log.e("erreur erreur",response.body()?.size.toString())
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Traitement>>?, t: Throwable?) {
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
        return inflater.inflate(R.layout.fragment_traitementmed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actif = false
        val btn_trt = view.findViewById<Button>(R.id.btn_trt)
        btn_trt.setOnClickListener {
            var dd = (txt_duree.text.toString().toInt()+7).toString()
            var t = Traitement(null,"$dd/03/2020",arguments?.getString("nss_pt"),arguments?.getInt("id_med"))
            addTrt(t)
            getTrtid(arguments?.getString("nss_pt"),arguments?.getInt("id_med"))
        }
    }


}
