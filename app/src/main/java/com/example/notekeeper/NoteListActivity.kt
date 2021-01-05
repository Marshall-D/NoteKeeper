package com.example.notekeeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        initializeDisplayContent()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initializeDisplayContent() {
        val listNotes: ListView = findViewById(R.id.list_notes)
        var notes: List<NoteInfo>? = DataManager.instance?.notes
        val adapterNotes: ArrayAdapter<NoteInfo>? =
            notes?.let { ArrayAdapter(this, android.R.layout.simple_list_item_1, it) }
        listNotes.adapter = adapterNotes

        listNotes.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, MainActivity::class.java)
//            var note: NoteInfo = listNotes.getItemAtPosition(position) as NoteInfo
            intent.putExtra(NOTE_POSITION, position)
            startActivity(intent)
        }
    }
}