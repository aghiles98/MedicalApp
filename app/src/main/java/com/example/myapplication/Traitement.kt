package com.example.myapplication

import androidx.room.Entity

@Entity
data class Traitement (
    var id : Int?,
    var date_fin : String?,
    var nss_pt : String?,
    var id_med : Int?
)