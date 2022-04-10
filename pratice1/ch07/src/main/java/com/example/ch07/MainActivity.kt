package com.example.ch07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_linear)
        //setContentView(R.layout.activity_relative)
        setContentView(R.layout.activity_grid)
    }
}