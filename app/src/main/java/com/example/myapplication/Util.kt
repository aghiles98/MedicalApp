package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri

class Util {

    /**
     * This function opens an URL
     */

    fun openPage(ctx: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ctx.startActivity(intent)
    }

}