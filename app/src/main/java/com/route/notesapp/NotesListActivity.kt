package com.route.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.route.notesapp.DataBase.Model.Note
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

        val simpleCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                deletedItemPos=position;
                val note = notesAdapter.removeItem(position)
                deletedNote=note
                showDeleteUndoAction(note)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recycler_view);
    }
    var deletedItemPos = -1;
    var deletedNote:Note?=null

    private fun showDeleteUndoAction(note: Note?) {
        val snackbar =
            Snackbar.make(recycler_view,"note deleted successfully",2000)
            snackbar.setAction("undo", View.OnClickListener {
                notesAdapter.addItem(deletedItemPos, note)
            })
        snackbar.addCallback(object :Snackbar.Callback(){
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                MyDataBase.getInstance(applicationContext)
                    ?.notesDao()
                    ?.deleteNote(note!!)
                deletedNote=null
            }
        })

        snackbar.show()
    }

    override fun onDestroy() {
        super.onDestroy()
            MyDataBase.getInstance(applicationContext)
                ?.notesDao()
                ?.deleteNote(deletedNote!!)
    }

    override fun onStart() {
        super.onStart()
        val data = MyDataBase.getInstance(applicationContext)
            ?.notesDao()
            ?.getAllNotes();
        notesAdapter.changeData(data?.toMutableList())

    }

}
