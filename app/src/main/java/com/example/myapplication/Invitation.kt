package com.example.myapplication

import androidx.room.Entity

@Entity
data class Invitation (
    var id :Int?,
    var nss_pt : String,
    var id_med : Int,
    var acceptee : Int
)