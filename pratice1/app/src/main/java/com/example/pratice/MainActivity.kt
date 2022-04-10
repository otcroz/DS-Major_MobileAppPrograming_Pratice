package com.example.pratice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // activity_main 화면 출력

        //val btn1 : Button = findViewById(R.id.button1); // 버튼 객체를 불러옴
        val btn2 = findViewById<Button>(R.id.button1) // 제네릭 타입을 사용



    }
}