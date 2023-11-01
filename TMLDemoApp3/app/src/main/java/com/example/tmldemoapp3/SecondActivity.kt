package com.example.tmldemoapp3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnRangePolygon = findViewById<Button>(R.id.btnRangePolygon)

        btnRangePolygon.setOnClickListener {
            val intent2 = Intent(this, ThirdActivity::class.java)
            startActivity(intent2)
        }

    }
}