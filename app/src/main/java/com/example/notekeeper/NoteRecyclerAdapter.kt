package com.example.notekeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter( mContext: Context, mListNotes: List<NoteInfo>?) : RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {
    private var layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var listNotes = mListNotes



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var note : NoteInfo = this!!.listNotes!![position]
        holder.textTitle.text= note.title
        holder.textCourse.text= note.course!!.title

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView : View = layoutInflater.inflate(R.layout.item_note_list,parent,false)
        return  ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listNotes!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textCourse : TextView = itemView.findViewById(R.id.text_note_text)
        var textTitle : TextView = itemView.findViewById(R.id.text_note_title)


    }
}