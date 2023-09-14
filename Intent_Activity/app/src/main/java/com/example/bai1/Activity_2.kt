package com.example.bai1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val tvSum = findViewById<TextView>(R.id.resultSum)
        val intent: Intent = getIntent()
        val num1: String? = intent.getStringExtra("num1")
        val num2: String? = intent.getStringExtra("num2")

        val checkNum1: Float? = num1?.toFloat()
        val checkNum2: Float? = num2?.toFloat()

        if (checkNum1 != null && checkNum2 != null) {
            val sum: Float = checkNum1 + checkNum2
            tvSum.text = sum.toString()
        } else {
            tvSum.text = "Error!!"
        }
    }
}