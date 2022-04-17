package com.example.ch13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val datas : MutableList<String>? = null
    lateinit var adapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //리사이클러 뷰 생성
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(datas)
        binding.mainRecyclerview.adapter = adapter
//        binding.mainRecyclerview.addItemDecoration(
//            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
//        )

        val requesteLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            val resultData = it.data?.getStringExtra("data3")
            Log.d("test_intent", "ActivityResultLauncher: $resultData")
        }

        // 인텐트 생성하기
        binding.fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("data1", "안녕하세요!")
            intent.putExtra("data2", " 저는 유수연입니다~!")
            //startActivity(intent)
            //startActivityForResult(intent, 10)
            requesteLauncher.launch(intent) // ActivityResultLauncher로 호출하기
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == RESULT_OK){
            val result = data?.getStringExtra("data1")
            Log.d("test_intent", "$result") // 이거 왜 안되지
        }
    }
}