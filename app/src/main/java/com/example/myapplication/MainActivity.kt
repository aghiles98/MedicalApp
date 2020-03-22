package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


public var connex = false
public var patient = false
public var actif = false

class MainActivity : AppCompatActivity() {



    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.fragment_liste)*/
        setContentView(R.layout.activity_splash)
        //getMedecin("beni douala","cardiologue")
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            setContentView(R.layout.activity_main)

        }, SPLASH_TIME_OUT)
    }

    override fun onBackPressed() {
        if (actif){

        }
        else {
            super.onBackPressed()
        }
    }
}
