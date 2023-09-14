package com.example.fragments_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // add fragment 1
        val frag1 = Fragment_1()
        supportFragmentManager.beginTransaction()
            .replace(R.id.Frag_A, frag1)
            .commit()

        // add fragment 2
        val frag2 = Fragment_2()
        supportFragmentManager.beginTransaction()
            .replace(R.id.Frag_B, frag2)
            .commit()
    }
}