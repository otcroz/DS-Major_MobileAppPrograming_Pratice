package com.example.ch13

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getStringExtra("data2")

        Log.d("test_intent", "$data1 $data2")

        // 암시적 인텐트
        binding.btn1.setOnClickListener {
            val intent = Intent()
            intent.action = "ACTION_EDIT"
            startActivity(intent)
        }

        // MainActivity로 되돌아가기
        /*binding.btn2.setOnClickListener {
            intent.putExtra("test3", "Yoosooyeon")
            Log.d("test_intent", "$intent")
            setResult(RESULT_OK, intent)
            finish()
        }*/

    }

    // 메뉴 바 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add_save){ // 아이템 "저장" 버튼을 눌렀을 때
            intent.putExtra("save_todo", binding.inputEdit.text.toString())
            setResult(RESULT_OK, intent)
            finish()

            return true
        }

        return false
    }
}