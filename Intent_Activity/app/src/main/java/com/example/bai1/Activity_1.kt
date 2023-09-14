package com.example.bai1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Activity_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val textView_1: EditText = findViewById(R.id.TV1)
        val textView_2: EditText = findViewById(R.id.TV2)
        val btnSum = findViewById<Button>(R.id.btnGetSum)

        btnSum.setOnClickListener{
            val intent: Intent = Intent(this@Activity_1, Activity_2::class.java)
            intent.putExtra("num1", textView_1.text.toString())
            intent.putExtra("num2", textView_2.text.toString())

            startActivity(intent)
        }
    }
}