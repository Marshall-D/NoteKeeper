package com.example.notekeeper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note_list.view.*
import kotlin.properties.Delegates

class NoteRecyclerAdapter(mListNotes: List<NoteInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listNotes = mListNotes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_note_list, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is NoteViewHolder -> {
                holder.bind(listNotes[position])
                holder.currentPosition = position
            }

        }
    }


    override fun getItemCount(): Int {
        return listNotes.size
    }


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val textTitle : TextView = itemView.text_title
        private val textCourse : TextView = itemView.text_course
        var currentPosition by Delegates.notNull<Int>()

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(NOTE_POSITION, currentPosition)
            context.startActivity(intent)

        }


        fun bind (note:NoteInfo) {
            textTitle.text = note.title
            textCourse.text = note.course!!.title

        }



    }


}