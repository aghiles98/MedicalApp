package com.example.myapplication

import androidx.room.Entity

@Entity
data class Medecintrt (
    var id : Int?,
    var nom : String,
    var prenom : String,
    var adresse : String,
    var commune : String,
    var specialite : String,
    var ouverture : Int,
    var fermeture : Int,
    var num_tel : String,
    var gps : String,
    var mdp : String,
    var initial: Int,
    var id_med: Int,
    var nss_pt: String
)