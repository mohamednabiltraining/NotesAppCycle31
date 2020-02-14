package com.route.notesapp.DataBase.daos

import androidx.room.*
import com.route.notesapp.DataBase.Model.Note


/**
 * Created by Mohamed Nabil Mohamed on 2/14/2020.
 * m.nabil.fci2015@gmail.com
 */
@Dao
interface NotesDao {
    @Insert
    fun insertNote(note:Note);
    @Delete
    fun deleteNote(note:Note)
    @Update
    fun updateNote(note:Note)

    @Query("delete from note where id= :id ")
    fun deleteNoteById(id:Int)

    @Query("select * from Note")
    fun getAllNotes():List<Note>

    @Query("select * from Note Where description like :word")
    fun searchNotesByDescription(word:String):List<Note>

}