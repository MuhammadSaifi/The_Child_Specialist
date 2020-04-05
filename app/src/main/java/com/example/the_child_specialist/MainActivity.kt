package com.example.the_child_specialist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.the_child_specialist.authentication.Authentication
import com.example.the_child_specialist.bmi.Calculator
import com.example.the_child_specialist.data.MyDbHandler
import com.example.the_child_specialist.data_nutrition.MyDb2Handler
import com.example.the_child_specialist.model.Db_Disease
import com.example.the_child_specialist.model_nutrition.DB_Nutrition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import maes.tech.intentanim.CustomIntent

class MainActivity : AppCompatActivity() {
    internal lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()


        //***************************************************************************
        val Bundle: Bundle? = intent.extras
        val rec_email = Bundle?.get("user_email")
        val view = navigationView.getHeaderView(0)
        val tv_output = view.findViewById<TextView>(R.id.output)
        tv_output.text = rec_email.toString()
//**********************************************************************************

        //******************Drawer Layout**************************
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                setTitle(R.string.app_name)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setTitle(R.string.app_name)
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    signout()
                    drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true

                }
                R.id.menu_about -> {
                    Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show()
                    drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                else -> super.onOptionsItemSelected(item)
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }

        //********************Drawer Layout************************

        //************************ Bottom Navigation *********************


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val fragment = findViewById<FrameLayout>(R.id.fragment)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        replaceFragment(Home())

        //***********************Bottom Navigation ***********************

        //********************db wala part **********************************


        val db = MyDbHandler(this)
        val db2 = MyDb2Handler(this)
        val add = Db_Disease()
        val add2 = DB_Nutrition()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.getBoolean("firstTime", false)) {
            add.id = 1
            add.name = "Fever"
            add.description = "A fever is not a disease. And It  can be also happend if we are walking in so hot days and we take a shower when our body is too hot and due to it we face this disease"
            db.addContact(add)
            add.id = 2
            add.name = "Flu"
            add.description = "Early signs of flue.Early signs of flue.Early signs of flue.Early signs of flue.Early signs of flue.Early signs of flue."
            db.addContact(add)
            add2.id2 = 1
            add2.name2 = "Milk"
            add2.description2 = "For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye."
            db2.addContact2(add2)

            add2.id2 = 2
            add2.name2 = "Chicken"
            add2.description2 = "For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye.For Strength Kay Lye."
            db2.addContact2(add2)
            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.apply()
        }



        //*******************db wala part ********************************


    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    title = "Child Specialist"
                    replaceFragment(Home())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.injection -> {
                    title = "Vaccination"
                    replaceFragment(Injection())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.calculator -> {
                    title = "Calculator"
                    replaceFragment(Calculator())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nutrients -> {
                    title = "Nutration"
                    replaceFragment(Nutration())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.disease -> {
                    title = "Disease"
                    replaceFragment(Disease())
                    return@OnNavigationItemSelectedListener true
                }
            }

            false

        }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this, "fadein-to-fadeout")
    }

    private fun signout() {
        mAuth.signOut()
        main_updateUI()
    }

    private fun main_updateUI() {
        val user = mAuth.currentUser
        if (user == null) {
            val log_intent = Intent(
                this,
                Authentication::class.java
            )
            startActivity(log_intent)
            return
        }
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(a)

    }

}

