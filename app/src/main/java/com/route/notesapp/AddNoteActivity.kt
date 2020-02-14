package com.route.notesapp

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.route.notesapp.DataBase.Model.Note
import com.route.notesapp.DataBase.MyDataBase
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        add_note_button.setOnClickListener({
            val titleText = titleTv.text.toString();
            val description = descriptionTv.text.toString();
            val dateText = dateTv.text.toString();
            val note = Note(
                title = titleText,
                description = description,
                time = dateText);
            MyDataBase.getInstance(applicationContext)
                ?.notesDao()
                ?.insertNote(note)
            showSuccessMessage()

        })
    }
    fun showSuccessMessage(){
        val builder = AlertDialog.Builder(this);
        builder.setMessage(R.string.note_created_successfully)
        builder.setPositiveButton(R.string.ok, {
                dialogInterface, i ->
            dialogInterface.dismiss()
                finish()
            })
        builder.setCancelable(false)
        builder.show()

    }
}
