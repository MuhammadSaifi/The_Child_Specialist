package com.example.the_child_specialist.authentication

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.the_child_specialist.MainActivity
import com.example.the_child_specialist.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.authentication.*
import maes.tech.intentanim.CustomIntent

class Authentication : AppCompatActivity() {
    private lateinit var mlog : Button
    internal lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentication)



    mlog= findViewById(R.id.btn_login)
    mlog.setOnClickListener {
        userlogin()
    }

        mAuth = FirebaseAuth.getInstance()

        btn_signup.setOnClickListener {
           sign_up()
       }

    }
    fun userlogin(){
        val intent = Intent(this, Log_In::class.java)
        startActivity(intent)

    }
    fun sign_up(){
        val movetosign = Intent(this, Sign_Up::class.java)
        startActivity(movetosign)
        CustomIntent.customType(this,"fadein-to-fadeout")
    }

    override fun onStart() {
        super.onStart()
        check_user()
    }
    private fun check_user() {
        val log_user = mAuth.currentUser
        if (log_user == null) {
            return
        } else {
            val eemail = log_user.email
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user_email",eemail)
            startActivity(intent)

        }
    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this,"fadein-to-fadeout")
    }

}