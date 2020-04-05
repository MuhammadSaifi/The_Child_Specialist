package com.example.the_child_specialist.authentication

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.the_child_specialist.MainActivity
import com.example.the_child_specialist.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import maes.tech.intentanim.CustomIntent

class Log_In : AppCompatActivity() {
    private lateinit var log_email: TextInputLayout
    private lateinit var log_password: TextInputLayout
    private lateinit var mlog_in: Button
    private lateinit var sign_move: TextView
    private lateinit var mProgress: ProgressBar
    internal lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()


        log_email = findViewById(R.id.log_email)
        log_password = findViewById(R.id.log_password)
        mlog_in = findViewById(R.id.mlog_in)
        sign_move = findViewById(R.id.sign_move)
        sign_move.setOnClickListener {
            val intent = Intent(this, Sign_Up::class.java)
            startActivity(intent)
            CustomIntent.customType(this, "fadein-to-fadeout")
        }

        mlog_in.setOnClickListener {
            closeKeyboard()
            singInUser()
        }

    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this, "fadein-to-fadeout")
    }


    private fun singInUser() {

        if (!validateEmailAddress() or !validatePassword()) {
            // Email or Password not valid,
            return
        }
        //Email and Password valid, sign in user here
        val email = log_email.editText!!.text.toString().trim { it <= ' ' }
        val password = log_password.editText!!.text.toString().trim { it <= ' ' }
        showProgressBar()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    hideProgressBar()
                    Toast.makeText(this, "Succesfull", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    CustomIntent.customType(this, "fadein-to-fadeout")
                    logincheck_user()

                } else {
                    hideProgressBar()
                    Toast.makeText(this, "Error Ocoured", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun logincheck_user() {
        val user = mAuth.currentUser
         if (user == null) {
             return
        } else {
            val log_email = user.email
             val intent = Intent(this, MainActivity::class.java)
             intent.putExtra("user_email",log_email)
             startActivity(intent)
             CustomIntent.customType(this, "fadein-to-fadeout")

         }
    }

    private fun validateEmailAddress(): Boolean {

        val email = log_email.editText!!.text.toString().trim { it <= ' ' }

        if (email.isEmpty()) {
            log_email.error = "Email is required. Can't be empty."
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            log_email.error = "Invalid Email."
            return false
        } else {
            log_email.error = null
            return true
        }
    }

    private fun validatePassword(): Boolean {

        val password = log_password.editText!!.text.toString().trim { it <= ' ' }

        if (password.isEmpty()) {
            log_password.error = "Password is required."
            return false
        } else if (password.length < 6) {
            log_password.error = "Password length short."
            return true
        } else {
            log_password.error = null
            return true
        }
    }

    private fun showProgressBar() {

       val prg = ProgressDialog(this)
        prg.show()
        //  prg.setMessage("Loading...")
        prg.window?.setContentView(R.layout.progress_dialog)
        prg.window?.setBackgroundDrawableResource(
            android.R.color.transparent
        )


    }

    private fun hideProgressBar() {
        val prg = ProgressDialog(this)
        prg.hide()

    }

    private fun closeKeyboard() {

        // here we have created the view and get the current focus of window.
        // if view not null then our system will be get the system service
        // and after that it will close the keyboard.
        val key = this.currentFocus
        if (key != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(key.windowToken, 0)
        }

    }
}