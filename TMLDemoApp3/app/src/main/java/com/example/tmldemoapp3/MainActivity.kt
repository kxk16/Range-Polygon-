package com.example.tmldemoapp3

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edt_txt_login = findViewById<EditText>(R.id.login_id)
        val edt_txt_password = findViewById<EditText>(R.id.password)

        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {


            val email = edt_txt_login.text.toString()
            val password = edt_txt_password.text.toString()

            if(email.isEmpty() or password.isEmpty()){
                Toast.makeText(this, "Kindly enter the credentials", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Authentication Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SecondActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this,"Authentication failed.",Toast.LENGTH_SHORT,).show()
                    }
                }
        }
    }
}