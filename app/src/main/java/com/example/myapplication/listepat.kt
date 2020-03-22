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
import kotlinx.android.synthetic.main.fragment_listepat.*
import kotlinx.android.synthetic.main.fragment_recherche.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class listepat : Fragment() {

    fun getPatienttrt(id_med:Int?) {
        val call = RetrofitService.endpoint.getpatienttrt(id_med!!)
        call.enqueue(object : Callback<List<Patienttrt>> {
            override fun onResponse(call: Call<List<Patienttrt>>?, response: Response<List<Patienttrt>>?) {
                if(response?.isSuccessful!!) {
                    var lv =view?.findViewById<ListView>(R.id.listepatt)
                    lv?.adapter = MyAdapter(listepatt.context, response.body())
                    Log.e("erreur size success",response.body()?.size.toString())
                    //Toast.makeText(mContext,liste!!.size, Toast.LENGTH_SHORT).show()

                }
                else{
                    Log.e("erreur erreur",response.body()?.size.toString())
                    Toast.makeText(activity,"erreur", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Patienttrt>>?, t: Throwable?) {
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
        return inflater.inflate(R.layout.fragment_listepat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actif = false
        getPatienttrt(arguments?.getInt("id_med"))
    }

    private class MyAdapter(context: Context?, list: List<Patienttrt>?): BaseAdapter(){
        private val mContext : Context?
        private val mlist : List<Patienttrt>?
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
            val rowMain = layoutInflater.inflate(R.layout.patientrow,viewGroup,false)
            val textViewvide5 = rowMain.findViewById<TextView>(R.id.textViewvide5)
            val textViewvide4 = rowMain.findViewById<TextView>(R.id.textViewvide4)
            val textViewvide3 = rowMain.findViewById<TextView>(R.id.textViewvide3)
            val textViewvide2 = rowMain.findViewById<TextView>(R.id.textViewvide2)
            val textViewvide1 = rowMain.findViewById<TextView>(R.id.textViewvide1)
            textViewvide3.text = mlist?.get(position)?.nom
            textViewvide2.text = mlist?.get(position)?.prenom
            textViewvide4.text = mlist?.get(position)?.num_tel
            textViewvide1.text = mlist?.get(position)?.adresse
            textViewvide5.text = mlist?.get(position)?.nss
            rowMain.setOnClickListener {
                var bundle = bundleOf("id_med" to mlist!!.get(position).id_med,"nss_pt" to mlist!!.get(position).nss_pt)
                viewGroup?.findNavController()?.navigate(R.id.action_listepat_to_traitementmed,bundle)
            }
            return rowMain
        }
    }


}
