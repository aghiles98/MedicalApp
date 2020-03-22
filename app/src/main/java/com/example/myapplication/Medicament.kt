package com.example.myapplication

import androidx.room.Entity

@Entity
data class Medicament (
    var id:Int?,
    var designation : String?
)