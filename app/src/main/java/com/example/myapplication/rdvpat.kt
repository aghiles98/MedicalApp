package com.example.myapplication


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_rdvpat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class rdvpat : Fragment() {
    fun getMedecinTrt(nss:String) {
        val call = RetrofitService.endpoint.getmedecintrt(nss)
        call.enqueue(object : Callback<List<Medecintrt>> {
            override fun onResponse(call: Call<List<Medecintrt>>?, response: Response<List<Medecintrt>>?) {
                if(response?.isSuccessful!!) {
                    var lv =view?.findViewById<ListView>(R.id.listetrt)
                    lv?.adapter = MyAdapter(listetrt.context,response.body())
                    Log.e("erreur size success",response.body()?.size.toString())
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()

                }
                else{
                    Log.e("erreur erreur",response.body()?.size.toString())
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Medecintrt>>?, t: Throwable?) {
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
        var ls =view?.findViewById<ListView>(R.id.listetrt)

        return inflater.inflate(R.layout.fragment_rdvpat, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actif = false
        var nss = arguments?.getString("nss")
        getMedecinTrt(nss!!)
    }

    private class MyAdapter(context: Context?, list: List<Medecintrt>?): BaseAdapter(){
        private val mContext : Context?
        private val mlist : List<Medecintrt>?
        init {
            mContext = context
            mlist = list
        }
        override fun getCount(): Int {
            return mlist!!.size
        }
        override fun getItemId(position: Int): Long {
            //To change body of created functions use File | Settings | File Templates.
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            //To change body of created functions use File | Settings | File Templates.
            return "azul"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            //To change body of created functions use File | Settings | File Templates.

            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.medecinrow,viewGroup,false)
            val textView34 = rowMain.findViewById<TextView>(R.id.textView34)
            val textView35 = rowMain.findViewById<TextView>(R.id.textView35)
            val textView36 = rowMain.findViewById<TextView>(R.id.textView36)
            val textView37 = rowMain.findViewById<TextView>(R.id.textView37)
            val textView38 = rowMain.findViewById<TextView>(R.id.textView38)
            val textView39 = rowMain.findViewById<TextView>(R.id.textView39)
            val textView40 = rowMain.findViewById<TextView>(R.id.textView40)
            textView34.text = mlist?.get(position)?.nom
            textView35.text = mlist?.get(position)?.prenom
            textView36.text = mlist?.get(position)?.num_tel
            textView37.text = mlist?.get(position)?.adresse
            textView38.text = mlist?.get(position)?.commune
            textView39.text = mlist?.get(position)?.specialite
            lien = mlist!!.get(position).gps
            val imageView17 = rowMain.findViewById<ImageView>(R.id.imageView17)
            val util = Util()
            // The facebook page URL
            val url  = lien
            imageView17.setOnClickListener{
                util.openPage(mContext!!,url)
            }
            val str = mlist?.get(position)?.ouverture.toString()+"h - "+mlist?.get(position)?.fermeture.toString()+"h"
            textView40.text = str
            rowMain.setOnClickListener {
                var bundle = bundleOf("id_med" to mlist!!.get(position).id,"nss_pt" to mlist!!.get(position).nss_pt)
                viewGroup?.findNavController()?.navigate(R.id.action_rdvpat_to_rdv_details,bundle)
            }
            return rowMain
        }
    }


}
