package com.example.myapplication

import androidx.room.Entity

@Entity
data class Medecin (
    var id : Int?,
    var nom : String,
    var prenom : String,
    var adresse : String,
    var commune : String,
    var specialite : String,
    var ouverture : String,
    var fermeture : String,
    var num_tel : String,
    var gps : String,
    var mdp : String,
    var initial: Int,
    var connecte: Int
)