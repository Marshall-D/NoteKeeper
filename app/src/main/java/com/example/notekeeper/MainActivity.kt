package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner


class MainActivity : AppCompatActivity() {

    private var mNote : NoteInfo? = null
    private var mIsNewNote: Boolean = false
    private lateinit var mTextNoteTitle: EditText
    private lateinit var mTextNoteText: EditText
    private lateinit var mSpinnerCourses: Spinner
    private  var mNotePosition : Int? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mSpinnerCourses = findViewById(R.id.spinner_courses)
        var course: MutableList<CourseInfo>? = DataManager.getInstance().courses
        val adapterCourses: ArrayAdapter<CourseInfo>? =
                course?.let { ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it) }
        adapterCourses?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinnerCourses.adapter = adapterCourses


        readDisplayStateValues()


        mTextNoteTitle = findViewById(R.id.text_note_title)
        mTextNoteText = findViewById(R.id.text_note_text)

        if(!mIsNewNote){
            displayNote(mSpinnerCourses, mTextNoteText, mTextNoteTitle)
        }

    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        //function for getting the notes from the textviews and spinners and saving it

        mNote?.course = mSpinnerCourses.selectedItem as CourseInfo
        mNote?.title = mTextNoteTitle.text.toString()
        mNote?.text = mTextNoteText.text.toString()
    }

    private fun displayNote(spinnerCourses: Spinner, textNoteText: EditText, textNoteTitle: EditText) {
        var courses: List<CourseInfo> = DataManager.getInstance().courses
        var courseIndex = courses.indexOf(mNote?.course)
        spinnerCourses.setSelection(courseIndex)

        textNoteTitle.setText(mNote?.title)
        textNoteText.setText(mNote?.text)

    }

    private fun readDisplayStateValues() {
        val intent: Intent = intent
        val position : Int = intent.getIntExtra(NOTE_POSITION, NOTE_POSITION_NOT_SET)

        mIsNewNote = position == NOTE_POSITION_NOT_SET

        if(mIsNewNote){
            createNewNote()

        } else {
            mNote = DataManager.getInstance().notes[position]

        }


    }

    private fun createNewNote() {
        val dm : DataManager = DataManager.getInstance()
        mNotePosition = dm.createNewNote()
        mNote = dm.notes[mNotePosition!!]

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
            R.id.action_send_mail -> {
                sendMail()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sendMail() {
        //function to get course title and note to send as mail with an implicit intent

        // getting the text on screen and saving to variables to send in the mail

        var course : CourseInfo = mSpinnerCourses.selectedItem as CourseInfo
        var subject : String = mTextNoteTitle.text.toString()
        var text : String = "check out what i leanred in the plural sight course" + course.title + " \n " + mTextNoteText.text.toString()

        // creating the implicit intent to send the mail
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc2822"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(intent)



    }
}