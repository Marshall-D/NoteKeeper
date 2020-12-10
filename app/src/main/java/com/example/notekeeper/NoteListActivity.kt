package com.example.notekeeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        initializeDisplayContent()

    }

    private fun initializeDisplayContent() {
        val listNotes: ListView = findViewById(R.id.list_notes)
        var notes: List<NoteInfo> = DataManager.getInstance().notes
        val adapterNotes: ArrayAdapter<NoteInfo>? =
            notes?.let { ArrayAdapter(this, android.R.layout.simple_list_item_1, it) }
        listNotes.adapter = adapterNotes

        listNotes.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, MainActivity::class.java)
            var note: NoteInfo = listNotes.getItemAtPosition(position) as NoteInfo
            intent.putExtra(NOTE_INFO, note)
            startActivity(intent)
        }
    }
}