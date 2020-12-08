package com.example.notekeeper

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
        val listnotes: ListView = findViewById(R.id.list_notes)
        var notes: List<NoteInfo> = DataManager.getInstance().notes
        val adapterNotes: ArrayAdapter<NoteInfo>? =
            notes?.let { ArrayAdapter(this, android.R.layout.simple_list_item_1, it) }
        listnotes.adapter = adapterNotes
    }
}