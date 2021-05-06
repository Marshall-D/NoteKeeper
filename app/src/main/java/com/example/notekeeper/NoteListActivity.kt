package com.example.notekeeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {
//    this is adapter for listview and we are using recycler view now
//    private var mAdapterNotes: ArrayAdapter<NoteInfo>? = null
      lateinit  var mNoteRecyclerAdapter: NoteRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        initializeDisplayContent()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
//    this is adapter for listview and we are using recycler view now

//        mAdapterNotes?.notifyDataSetChanged()
        mNoteRecyclerAdapter.notifyDataSetChanged()
    }

    private fun initializeDisplayContent() {
//        val listNotes: ListView = findViewById(R.id.list_notes)
//        var notes: List<NoteInfo>? = DataManager.instance?.notes
//        mAdapterNotes = notes?.let { ArrayAdapter(this, android.R.layout.simple_list_item_1, it) }
//        listNotes.adapter = mAdapterNotes
//
//        listNotes.setOnItemClickListener { _, _, position, _ ->
//            val intent = Intent(this, MainActivity::class.java)
////          var note: NoteInfo = listNotes.getItemAtPosition(position) as NoteInfo
//            intent.putExtra(NOTE_POSITION, position)
//            startActivity(intent)
//        }

//      create a local variable to hold the reference to the recycler view xml file created
//        val recyclerNotes : RecyclerView = findViewById(R.id.list_notes)
//      create a layout manager to handle the arrangement of each item in the recycler view
        val notesLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
//      connecting the layout manager to the recycler view
        list_notes.layoutManager = notesLayoutManager
         // get the list of notes and add them to the recycler view through the adapter
        var notesList: List<NoteInfo>  = DataManager.instance!!.notes
       mNoteRecyclerAdapter = NoteRecyclerAdapter( notesList)
        list_notes.adapter = mNoteRecyclerAdapter

    }
}