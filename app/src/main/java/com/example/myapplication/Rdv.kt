package com.example.myapplication

import androidx.room.Entity
import java.util.*

@Entity
data class Rdv (
    var id : Int?,
    var date_rdv : String?,
    var heure_rdv : String?,
    var nss_pt : String?,
    var id_med : Int?
)