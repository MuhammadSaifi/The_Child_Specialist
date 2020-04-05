package com.example.the_child_specialist.authentication

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.the_child_specialist.MainActivity
import com.example.the_child_specialist.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import maes.tech.intentanim.CustomIntent

class Sign_Up : AppCompatActivity() {
    internal lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()


        val email = findViewById<TextInputLayout>(R.id.edit_email)
        val password = findViewById<TextInputLayout>(R.id.edit_password)
        val register = findViewById<Button>(R.id.register)
        val move_log = findViewById<TextView>(R.id.log_move)
        move_log.setOnClickListener {
            val intent_log = Intent(this, Log_In::class.java)
            startActivity(intent_log)
            CustomIntent.customType(this, "fadein-to-fadeout")
        }

        register.setOnClickListener {
            close()
            createUser()
        }

    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this, "fadein-to-fadeout")
    }

    private fun createUser() {

        if (!validateEmailAddress() or !validatePassword()) {
            // Email or Password not valid,
            return
        }
        //Email and Password valid, create user here
        val email = edit_email.editText!!.text.toString().trim { it <= ' ' }
        val password = edit_password.editText!!.text.toString().trim { it <= ' ' }
        showDialog()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    hideDialog()
                    Toast.makeText(this, "Succesfull!", Toast.LENGTH_SHORT).show()
                    updateUI()
                } else {
                    hideDialog()
                    Toast.makeText(this, "Error Ocoured", Toast.LENGTH_SHORT).show()
                }
            }


    }

    private fun updateUI() {
        val user = mAuth.currentUser
        // when open again then it check user logged in or not if user == null
        // then user not logged in
        // but if user logged so see else condition.
        if (user == null) {
            hideDialog()
//            mOutputText.text = "User Not Logged In"
            return
        } else {
            hideDialog()
            val sign_email = user.email
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user_email",sign_email)
            startActivity(intent)
            CustomIntent.customType(this, "fadein-to-fadeout")

            //        mOutputText.text = user.email
        }
    }

    /*override fun onStart() {
        super.onStart()
        updateUI()
    }*/

    private fun validateEmailAddress(): Boolean {

        val email = edit_email.editText!!.text.toString().trim { it <= ' ' }

        if (email.isEmpty()) {
            edit_email.error = "Email is required. Can't be empty."
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_email.error = "Invalid Email. Enter valid email address."
            return false
        } else {
            edit_email.error = null
            return true
        }
    }

    private fun validatePassword(): Boolean {

        val password = edit_password.editText!!.text.toString().trim { it <= ' ' }

        if (password.isEmpty()) {
            edit_password.error = "Password is required."
            return false
        } else if (password.length < 6) {
            edit_password.error = "Password length short. Minimum 6 characters required."
            return true
        } else {
            edit_password.error = null
            return true
        }
    }

    private fun showDialog() {
        val show_d = ProgressDialog(this)
        show_d.show()
        show_d.setContentView(R.layout.progress_dialog)
        show_d.window?.setBackgroundDrawableResource(
            android.R.color.transparent
        )
    }

    private fun hideDialog() {
        val hide_dia = ProgressDialog(this)
        hide_dia.hide()

    }

    private fun close() {

        // here we have created the view and get the current focus of window.
        // if view not null then our system will be get the system service
        // and after that it will close the keyboard.
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }
}