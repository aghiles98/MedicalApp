package com.example.myapplication

import androidx.room.Entity

@Entity
data class Ligne_trt (
    var id :Int?,
    var heure : String,
    var id_trt : Int,
    var id_mdcm : Int?
)