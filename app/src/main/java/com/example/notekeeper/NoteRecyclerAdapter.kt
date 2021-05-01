package com.example.notekeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter(private val mContext: Context) : RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {
    private var layoutInflater: LayoutInflater = LayoutInflater.from(mContext)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView : View = layoutInflater.inflate(R.layout.item_note_list,parent,false)
        return  ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textCourse : TextView = itemView.findViewById(R.id.text_note_text)
        var textTitle : TextView = itemView.findViewById(R.id.text_note_title)


    }
}