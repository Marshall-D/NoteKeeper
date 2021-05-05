package com.example.notekeeper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note_list.view.*

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
                holder.bind(listNotes.get(position))
            }

        }
    }


    override fun getItemCount(): Int {
        return listNotes.size
    }
    fun submitList(mListNotes: List<NoteInfo>){
        listNotes = mListNotes
    }


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle : TextView = itemView.text_title
        val textCourse : TextView = itemView.text_course

        fun bind (note:NoteInfo) {
            textTitle.text = note.title
            textCourse.text = note.course!!.title

        }


    }


}