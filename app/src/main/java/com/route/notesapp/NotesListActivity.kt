package com.route.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.notesapp.DataBase.MyDataBase
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.android.synthetic.main.content_notes_list.*

class NotesListActivity : AppCompatActivity() {

    var notesAdapter=NotesAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        setSupportActionBar(toolbar)
        recycler_view.adapter=notesAdapter

        fab.setOnClickListener { view ->
            val intent = Intent(this@NotesListActivity,
                AddNoteActivity::class.java)
            startActivity(intent)
            /*
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", {
                            }).show()
            */

        }

    }

    override fun onStart() {
        super.onStart()
        val data = MyDataBase.getInstance(applicationContext)
            ?.notesDao()
            ?.getAllNotes();
        notesAdapter.changeData(data)

    }

}
