package com.route.notesapp

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by Mohamed Nabil Mohamed on 2/21/2020.
 * m.nabil.fci2015@gmail.com
 */
class DataHandler {
    companion object {
        private lateinit var sharedPreferences: SharedPreferences;
        val preferencesName = "app-Preferences"

        fun init(context: Context) {
            sharedPreferences = context.getSharedPreferences(
                preferencesName,Context.MODE_PRIVATE);
        }
        fun getString(key:String,defValue:String?)
                =sharedPreferences.getString(key,defValue);

        fun getInt(key:String,defValue:Int)
                =sharedPreferences.getInt(key,defValue);
        fun saveString(key:String,value:String?){
           val editor= sharedPreferences.edit()
            editor.putString(key,value);
            editor.apply();
        }
        fun saveInt(key:String,value:Int){
           val editor= sharedPreferences.edit()
            editor.putInt(key,value);
            editor.apply();
        }
        fun removeAllData(){
            sharedPreferences.edit().clear().apply();
        }



    }
}