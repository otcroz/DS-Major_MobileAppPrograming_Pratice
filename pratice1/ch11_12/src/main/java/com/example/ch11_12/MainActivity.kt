package com.example.ch11_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 액션바에서 메뉴 생성
            //1) 하드코딩으로 작성
            //val menuItem1 : MenuItem? = menu?.add(0,0,0, "메뉴1")
            //val menuItem2 : MenuItem? = menu?.add(0,1,0, "메뉴2")

            //2) menu xml 파일 만들기: menuInflater
            menuInflater.inflate(R.menu.menu_main, menu)

        //2. 액션 뷰: 검색 메뉴에 대한 코드
        val menuSearch = menu?.findItem(R.id.menu_search) // 메뉴 바인딩
        val searchView = menuSearch?.actionView as SearchView // 액션 뷰 가져오기
 
        searchView.setOnQueryTextListener (object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어 변경 이벤트
                Log.d("test_app", "검색어 변경~!")

                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드 검색 버튼을 클릭한 순간의 이벤트
                Log.d("test_app", "버튼 클릭")
                return true
            }
        } ) 

        return super.onCreateOptionsMenu(menu)

    }


}