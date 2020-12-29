package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner


class MainActivity : AppCompatActivity() {

    private var mNote : NoteInfo? = null
    private var mIsNewNote: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val spinnerCourses: Spinner = findViewById(R.id.spinner_courses)
        var course: MutableList<CourseInfo>? = DataManager.getInstance().courses
        val adapterCourses: ArrayAdapter<CourseInfo>? =
                course?.let { ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it) }
        adapterCourses?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourses.adapter = adapterCourses


        readDisplayStateValues()


        var textNoteTitle: EditText = findViewById(R.id.text_note_title)
        var textNoteText: EditText = findViewById(R.id.text_note_text)

        if(!mIsNewNote){
            displayNote(spinnerCourses, textNoteText, textNoteTitle)
        }

    }

    private fun displayNote(spinnerCourses: Spinner, textNoteText: EditText, textNoteTitle: EditText) {
        var courses: List<CourseInfo> = DataManager.getInstance().courses
        var courseIndex = courses.indexOf(mNote?.course)
        spinnerCourses.setSelection(courseIndex)

        textNoteTitle.setText(mNote?.text)
        textNoteText.setText(mNote?.title)

    }

    private fun readDisplayStateValues() {
        val intent: Intent = intent
        mNote = intent.getParcelableExtra(NOTE_INFO)

        if ( mNote == null){
            mIsNewNote = true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}