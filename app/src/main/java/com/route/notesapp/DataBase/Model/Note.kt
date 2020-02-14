package com.route.notesapp.DataBase.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Mohamed Nabil Mohamed on 2/14/2020.
 * m.nabil.fci2015@gmail.com
 */
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:String,
    @ColumnInfo
    val title:String,
    @ColumnInfo
    val description:String,
    @ColumnInfo
    val time:String)