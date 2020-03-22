package com.example.myapplication

import androidx.room.Entity

@Entity
data class Traitant (
    var nss_pt : String,
    var id_med : Int
)