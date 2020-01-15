package com.example.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bookhub.*
import com.example.bookhub.fragment.AboutAppFragment
import com.example.bookhub.fragment.DashboardFragment
import com.example.bookhub.fragment.FavouritesFragment
import com.example.bookhub.fragment.profileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var draweLayout:DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var frameLayout: FrameLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var navigationView:NavigationView

    var previousMenuItem:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        draweLayout=findViewById(R.id.drawerlayout)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        frameLayout=findViewById(R.id.frame)
        toolbar=findViewById(R.id.toolbar1)
        navigationView=findViewById(R.id.NavigationView)

        setUpToolbar()
        openDashboard()

        val actionBarDrawerToggle=ActionBarDrawerToggle(this@MainActivity,draweLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        draweLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()


        navigationView.setNavigationItemSelectedListener {


            if (previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it

            when(it.itemId){
                R.id.dashboard ->{openDashboard()
                    draweLayout.closeDrawers()
                }
                R.id.favourites ->{supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frame,
                        FavouritesFragment()
                    )
                    .commit()
                    supportActionBar?.title="Favourites"
                    draweLayout.closeDrawers()}

                R.id.profile ->{supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frame,
                        profileFragment()
                    )
                    .commit()
                    supportActionBar?.title="Profile"
                draweLayout.closeDrawers()
                }
                R.id.aboutApp ->{supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.frame,
                        AboutAppFragment()
                    )
                    .commit()
                    supportActionBar?.title="About App"
                    draweLayout.closeDrawers()}
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "ToolBar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId
        if (id==android.R.id.home)
        {
            draweLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDashboard(){
        val fragment= DashboardFragment()
        val transaction =supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }


    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frame)


        when(frag){
            !is DashboardFragment -> openDashboard()

            else->super.onBackPressed()
        }

    }
}
