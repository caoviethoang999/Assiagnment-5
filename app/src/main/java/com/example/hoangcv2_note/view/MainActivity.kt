package com.example.hoangcv2_note.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hoangcv2_note.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recylerFragment = ListNoteFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, recylerFragment)
            .addToBackStack(null).commit()
    }
}