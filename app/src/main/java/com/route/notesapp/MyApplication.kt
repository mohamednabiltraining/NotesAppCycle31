package com.route.notesapp

import android.app.Application


/**
 * Created by Mohamed Nabil Mohamed on 2/21/2020.
 * m.nabil.fci2015@gmail.com
 */
class MyApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        DataHandler.init(this)
    }
}