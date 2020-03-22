package com.example.myapplication


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_rdvpat.*
import kotlinx.android.synthetic.main.fragment_recherche.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
var lien : String = ""
class recherche : Fragment() {
    fun getMedecin(commune:String,specialite:String,nss_pt:String?) {
        val call = RetrofitService.endpoint.detailmedecin(commune,specialite)
        call.enqueue(object : Callback<List<Medecin>> {
            override fun onResponse(call: Call<List<Medecin>>?, response: Response<List<Medecin>>?) {
                if(response?.isSuccessful!!) {
                    Log.e("erreur size success",response.body()?.size.toString())
                    var lv =view?.findViewById<ListView>(R.id.listemed)
                    if (nss_pt==null){
                        lv?.adapter = MyAdapter(listemed.context,response.body(),"")
                    }
                    else{
                        lv?.adapter = MyAdapter(listemed.context,response.body(),nss_pt!!)
                    }

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recherche, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actif = false
        val button21 = view.findViewById<Button>(R.id.button21)
        var nss_pt = arguments?.getString("nss")
        button21.setOnClickListener {
            if (connex){
                getMedecin(editText21.text.toString(),editText20.text.toString(),nss_pt)
            }
            else{
                getMedecin(editText21.text.toString(),editText20.text.toString(),null)
            }



            //Log.e("erreur size","sahitoufd")
            //var listmed = getMedecin(editText21.text.toString(),editText20.text.toString())
            //Log.e("erreur size",listmed?.size.toString())
        }

    }
    private class MyAdapter(context:Context?,list: List<Medecin>?,nss_pt: String): BaseAdapter(){
        private val mContext : Context?
        private val mlist : List<Medecin>?
        private val nss : String
        init {
            mContext = context
            mlist = list
            nss = nss_pt
        }
        private fun addInvitation(i: Invitation) {
            val call = RetrofitService.endpoint.addinvitation(i)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    if(response?.isSuccessful!!) {
                        Toast.makeText(mContext,"ENVOI REUSSI",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<String>?, t: Throwable?) {
                    Log.e("erreur retrofit",t.toString())
                    // Un toast pour l'utilisateur
                    Toast.makeText(mContext,"ECHEC ENVOI",Toast.LENGTH_SHORT).show()
                }
            }
            )

        }
        fun getInvitation(nss:String,id_med:Int) : Int{
            var acceptee = 0
            val call = RetrofitService.endpoint.getinvitation(nss,id_med)
            call.enqueue(object : Callback<List<Invitation>> {
                override fun onResponse(call: Call<List<Invitation>>?, response: Response<List<Invitation>>?) {
                    if(response?.isSuccessful!!) {
                        Log.e("erreur size success",response.body()?.size.toString())
                        //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()
                        if (response.body()!!.size>0){
                            if (response!!.body()?.get(0)?.acceptee==1){
                                acceptee = 1
                            }
                        }

                    }
                    else{
                        Log.e("erreur erreur",response.body()?.size.toString())
                        Toast.makeText(mContext,"erreur", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<List<Invitation>>?, t: Throwable?) {
                    Log.e("erreur retrofit",t.toString())
                    // Un toast pour l'utilisateur
                    Toast.makeText(mContext,"ECHEC INSCRIPTION", Toast.LENGTH_SHORT).show()
                }
            }
            )
            return acceptee
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
            var rowMain:View
            if (connex){
                rowMain = layoutInflater.inflate(R.layout.medecin2row,viewGroup,false)
                val imageView36 = rowMain.findViewById<ImageView>(R.id.imageView36)
                imageView36.setTag(R.drawable.add)
                imageView36.setOnClickListener{
                    if (imageView36.getTag()==R.drawable.attente){
                        getInvitation(nss,mlist?.get(position)?.id!!.toInt())
                    }
                    else if(imageView36.getTag()==R.drawable.add){
                        var i = Invitation(null,nss,mlist?.get(position)?.id!!.toInt(),0)
                        addInvitation(i)
                        Log.e("nss",nss)
                        Log.e("nss",mlist?.get(position)?.id!!.toInt().toString())

                        imageView36.setImageResource(R.drawable.attente)
                    }

                }
            }
            else{
                rowMain = layoutInflater.inflate(R.layout.medecinrow,viewGroup,false)
            }
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
            return rowMain
        }
    }


}
