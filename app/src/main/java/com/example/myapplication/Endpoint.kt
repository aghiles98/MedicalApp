package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Endpoint {
    @POST("addpatient")
    fun addpatient(@Body p: Patient): Call<String>
    @POST("addinvitation")
    fun addinvitation(@Body i: Invitation): Call<String>
    @POST("addtrt")
    fun addtrt(@Body t: Traitement): Call<String>
    @POST("addmedecin")
    fun addmedecin(@Body m: Medecin): Call<String>
    @GET("getpatienttrt/{id_med}")
    fun getpatienttrt(@Path("id_med") id_med:Int): Call<List<Patienttrt>>
    @GET("getinvitation/{nss}/{id_med}")
    fun getinvitation(@Path("nss") nss:String,@Path("id_med") id_med:Int): Call<List<Invitation>>
    @GET("getmedecin/{commune}/{specialite}")
    fun detailmedecin(@Path("commune") commune:String,@Path("specialite") specialite:String): Call<List<Medecin>>
    // Envoi d'un paramètre book dans le message body
    @GET("getmedecinverify/{num_tel}/{mdp}")
    fun verifymedecin(@Path("num_tel") num_tel:String,@Path("mdp") mdp:String): Call<List<Medecin>>
    @GET("updatemedecin/{num_tel}/{mdp}")
    fun updatemedecin(@Path("num_tel") num_tel:String,@Path("mdp") mdp:String): Call<String>
    @GET("getpatientverify/{num_tel}/{mdp}")
    fun verifypatient(@Path("num_tel") num_tel:String,@Path("mdp") mdp:String): Call<List<Patient>>
    @GET("updatepatient/{num_tel}/{mdp}")
    fun updatepatient(@Path("num_tel") num_tel:String,@Path("mdp") mdp:String): Call<String>
    @GET("getmedecintrt/{nss}")
    fun getmedecintrt(@Path("nss") nss:String): Call<List<Medecintrt>>
    @POST("addrdv")
    fun addrdv(@Body d: Rdv): Call<String>
    @GET("gettrtid/{nss}/{id_med}")
    fun gettrtid(@Path("nss") nss:String, @Path("id_med") id_med:Int): Call<List<Traitement>>
    @POST("addlignetrt")
    fun addlignetrt(@Body l: Ligne_trt): Call<String>
    @POST("addmedicament")
    fun addmedicament(@Body m: Medicament): Call<String>
    @GET("getmdcmid/{designation}")
    fun getmdcm(@Path("designation") designation:String): Call<List<Medicament>>
}