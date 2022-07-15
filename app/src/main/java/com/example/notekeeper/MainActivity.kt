package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_navigation.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var mNoteRecyclerAdapter: NoteRecyclerAdapter


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
//        navView.setNavigationItemSelectedListener(this)
        navView.setNavigationItemSelectedListener { MenuItem ->
            when (MenuItem.itemId) {
                R.id.nav_courses -> {
                    Log.i("cOURSES", "Sign out clicked!")

                    Toast.makeText(this, "COURSES111", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.nav_notes -> {
                    Log.i("Notes", "Sign out clicked!")

                    Toast.makeText(this, "NOTRES", Toast.LENGTH_LONG).show()
                    true
                }R.id.nav_share -> {
                Log.i("Share", "Sign out clicked!")

                Toast.makeText(this, "SARE", Toast.LENGTH_LONG).show()

                true
            }R.id.nav_send -> {
                Log.i("Send", "Sign out clicked!")

                Toast.makeText(this, "SEND", Toast.LENGTH_LONG).show()

                true
            }
                else  ->
                {false}

            }

        }

//        binding.navView.setNavigationItemSelectedListener(this)

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
    }


    override fun onResume() {
        super.onResume()

        mNoteRecyclerAdapter.notifyDataSetChanged()
    }

    private fun initializeDisplayContent() {

//      create a layout manager to handle the arrangement of each item in the recycler view
        val notesLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
//      connecting the layout manager to the recycler view
        list_items.layoutManager = notesLayoutManager
        // get the list of notes and add them to the recycler view through the adapter
        var notesList: List<NoteInfo> = DataManager.instance!!.notes
        mNoteRecyclerAdapter = NoteRecyclerAdapter(notesList)
        list_items.adapter = mNoteRecyclerAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId

//        when (item.itemId) {
//            R.id.nav_courses -> {
//                Log.i("cOURSES", "Sign out clicked!")
//
//                Toast.makeText(this, "COURSES111", Toast.LENGTH_LONG).show()
//            }
//            R.id.nav_notes -> {
//                Log.i("Notes", "Sign out clicked!")
//
//                Toast.makeText(this, "NOTRES", Toast.LENGTH_LONG).show()
//            }R.id.nav_share -> {
//            Log.i("Share", "Sign out clicked!")
//
//            Toast.makeText(this, "SARE", Toast.LENGTH_LONG).show()
//            }R.id.nav_send -> {
//            Log.i("Send", "Sign out clicked!")
//
//            Toast.makeText(this, "SEND", Toast.LENGTH_LONG).show()
//            }
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

//    private fun handleSelection(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}