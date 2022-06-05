package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_note_open.*

class NoteOpen : AppCompatActivity() {
    private var noteposition = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_open)
        val adapterCourses= ArrayAdapter<CourseInfo>(this,android.R.layout.simple_spinner_item,DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter=adapterCourses

        noteposition = savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET)?:
        intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        if(noteposition!= POSITION_NOT_SET){
            displayNote()
        }
        else
        {
            DataManager.notes.add((NoteInfo()))
            noteposition=DataManager.notes.lastIndex
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings->true
            R.id.action_next ->{
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++noteposition
        displayNote()
        invalidateOptionsMenu()
    }


    private fun displayNote() {
        val note = DataManager.notes[noteposition]
        notetitle.setText(note.title)
        notetext.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spin.setSelection(coursePosition)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(noteposition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next)
            if(menuItem!=null){
                menuItem.icon=getDrawable(R.drawable.stop)
                menuItem.isEnabled=false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note=DataManager.notes[noteposition]
        note.title=notetitle.text.toString()
        note.text=notetext.text.toString()
        note.course=spin.selectedItem as CourseInfo
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(EXTRA_NOTE_POSITION,noteposition)
    }
}