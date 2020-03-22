package com.example.myapplication

import androidx.room.Entity

@Entity
data class Patienttrt (
    var nss : String,
    var nom : String,
    var prenom : String,
    var adresse : String,
    var num_tel : String,
    var mdp : String,
    var initial: Int,
    var connecte: Int,
    var id_med: Int?,
    var nss_pt: String
)