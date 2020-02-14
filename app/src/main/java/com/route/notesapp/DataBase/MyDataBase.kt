package com.route.notesapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.notesapp.DataBase.Model.Note
import com.route.notesapp.DataBase.daos.NotesDao


/**
 * Created by Mohamed Nabil Mohamed on 2/14/2020.
 * m.nabil.fci2015@gmail.com
 */

@Database(entities = arrayOf(Note::class), exportSchema = false,version = 1)
abstract class MyDataBase :RoomDatabase(){
    abstract fun notesDao():NotesDao
    companion object{
         private var myDataBaseInstance:MyDataBase?=null
        fun getInstance(context: Context):MyDataBase?{
            if (myDataBaseInstance==null){
                myDataBaseInstance =
                    Room.databaseBuilder(context,
                        MyDataBase::class.java,"NotesDataBase")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
            return myDataBaseInstance;
        }
    }
}