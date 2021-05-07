package com.example.notekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_navigation.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
//    lateinit var toggle: ActionBarDrawerToggle
    lateinit  var mNoteRecyclerAdapter: NoteRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        initializeDisplayContent()
    }




    override fun onResume() {
        super.onResume()

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
        list_items.layoutManager = notesLayoutManager
        // get the list of notes and add them to the recycler view through the adapter
        var notesList: List<NoteInfo>  = DataManager.instance!!.notes
        mNoteRecyclerAdapter = NoteRecyclerAdapter( notesList)
        list_items.adapter = mNoteRecyclerAdapter

    }
//

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