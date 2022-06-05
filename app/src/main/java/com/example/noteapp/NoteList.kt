package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_open.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val act= Intent(this,NoteOpen::class.java)
            startActivity(act)
        }
        noteLists.adapter= ArrayAdapter(this,
            android.R.layout.simple_list_item_1,DataManager.notes)

        noteLists.setOnItemClickListener { parent, view, position, id ->
            val activityIntent =Intent(this,NoteOpen::class.java)
            activityIntent.putExtra("EXTRA_NOTE_POSITION",position)
            startActivity(activityIntent)
        }

    }


    override  fun onResume() {
        super.onResume()
        (noteLists.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}