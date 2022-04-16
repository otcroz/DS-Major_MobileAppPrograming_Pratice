package com.example.ch11_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }

    // 액션바에서 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //1. 하드코딩으로 작성
        //val menuItem1 : MenuItem? = menu?.add(0,0,0, "메뉴1")
        //val menuItem2 : MenuItem? = menu?.add(0,1,0, "메뉴2")

        //2. menu xml 파일 만들기: menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)

    }


}