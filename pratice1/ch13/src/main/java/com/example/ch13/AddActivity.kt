package com.example.ch13

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")

        Log.d("test_intent", "$data1 $data2")

        // MainActivity로 되돌아가기
        /*binding.btn2.setOnClickListener {
            intent.putExtra("test3", "Yoosooyeon")
            Log.d("test_intent", "$intent")
            setResult(RESULT_OK, intent)
            finish()
        }*/

    }
}