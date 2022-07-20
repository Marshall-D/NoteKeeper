package com.example.notekeeper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_courses_list.view.*
import kotlinx.android.synthetic.main.item_note_list.view.*
import kotlinx.android.synthetic.main.item_note_list.view.text_course
import kotlin.properties.Delegates

class CourseRecyclerAdapter(mListCourses: List<CourseInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listCourses = mListCourses

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_courses_list, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is CourseViewHolder -> {
                holder.bind(listCourses[position])
                holder.currentPosition = position
            }

        }
    }


    override fun getItemCount(): Int {
        return listCourses.size
    }


    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val textTitle : TextView = itemView.text_course
        var currentPosition by Delegates.notNull<Int>()

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            Toast.makeText(context,listCourses[currentPosition].title, Toast.LENGTH_LONG).show()

        }


        fun bind (course: CourseInfo) {
            textTitle.text = course.title

        }



    }


}