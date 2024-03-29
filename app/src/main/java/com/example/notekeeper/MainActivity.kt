package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_navigation.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mNoteRecyclerAdapter: NoteRecyclerAdapter
    private lateinit var mCourseRecyclerAdapter: CourseRecyclerAdapter
    lateinit var coursesLayoutManager: RecyclerView.LayoutManager
    lateinit var notesLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {

            val intent = Intent(this, NoteActivity::class.java)
            this.startActivity(intent)


        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)


        val navController = findNavController(R.id.nav_host_fragment)
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_notes, R.id.nav_courses, R.id.nav_send, R.id.nav_share
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initializeDisplayContent()

        navController.addOnDestinationChangedListener { _, destination, _ ->


            when (destination.id) {
                R.id.nav_notes -> {
                    displayNotes()
                }
                R.id.nav_courses -> {

                    displayCourses()
                    Log.i("courses", "Sign out clicked!")

                    Toast.makeText(this, getString(R.string.courses), Toast.LENGTH_LONG).show()
                }
                R.id.nav_send -> {
                    Log.i("send", "Sign out clicked!")

                    Toast.makeText(this, "send", Toast.LENGTH_LONG).show()

                }
                R.id.nav_share -> {
                    Log.i("share", "Sign out clicked!")

                    Toast.makeText(this, "share", Toast.LENGTH_LONG).show()

                }

            }

        }

    }



    override fun onResume() {
        super.onResume()
// notify adapter that dataset might have changed
        mNoteRecyclerAdapter.notifyDataSetChanged()
        mCourseRecyclerAdapter.notifyDataSetChanged()

    }

    private fun initializeDisplayContent() {

//      create a layout manager to handle the arrangement of each item in the recycler view.
//
        notesLayoutManager = LinearLayoutManager(this)
        coursesLayoutManager = GridLayoutManager(this,resources.getInteger(R.integer.course_grid_span))

//      connecting the layout manager to the recycler view
        list_items.layoutManager = notesLayoutManager
        // get the list of notes and add them to the recycler view through the adapter
        var notesList: List<NoteInfo> = DataManager.instance!!.notes
        mNoteRecyclerAdapter = NoteRecyclerAdapter(notesList)
        // get the list of courses and add them to the recycler view through the adapter

        var coursesList: List<CourseInfo> = DataManager.instance!!.courses
        mCourseRecyclerAdapter = CourseRecyclerAdapter(coursesList)



        displayNotes()


    }

    private fun displayCourses() {
        //      set the layout manager for the recycler view
        list_items.layoutManager =  coursesLayoutManager
        // associate the adapter for recycler view with whichever adapter containing data you want
        list_items.adapter = mCourseRecyclerAdapter

    }
    private fun displayNotes() {
        //      set the layout manager for the recycler view

        list_items.layoutManager = notesLayoutManager
        // associate the adapter for recycler view with whichever adapter containing data you want

        list_items.adapter = mNoteRecyclerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}