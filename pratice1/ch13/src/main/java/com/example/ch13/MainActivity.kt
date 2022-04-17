package com.example.ch13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var datas : MutableList<String>? = null
    lateinit var adapter : MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ActivityResultLauncher 사용하기: intent 사후처리
        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            val resultData = it.data!!.getStringExtra("save_todo")?.let{
                datas?.add(it) // datas에 데이터 추가
                adapter.notifyDataSetChanged() // 리사이클러 뷰의 데이터 변경 명시
            }
        }

        // 액티비티가 비활성 -> 활성되었을 때 Bundle에 저장되었던 datas의 값을 받는다.
        datas = savedInstanceState?.let{
            it.getStringArrayList("mydatas")?.toMutableList() // key 값을 통해 값을 얻어온다.
        } ?: let{ // null일 때
            mutableListOf<String>() // 리스트 생성 및 선언
        }

        //** 데이터를 가지고 온 후에 리사이클러 뷰 생성
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(datas)
        binding.mainRecyclerview.adapter = adapter
        binding.mainRecyclerview.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

        // 인텐트 생성하기
        binding.fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("data1", "안녕하세요!")
            intent.putExtra("data2", " 저는 유수연입니다~!")
            //startActivity(intent)
            //startActivityForResult(intent, 10)
            requestLauncher.launch(intent) // ActivityResultLauncher로 호출하기
        }


    }

    // datas의 값을 저장
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("mydatas", ArrayList(datas)) // Bundle에 ArrayList 값 저장
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == RESULT_OK){
            val result = data?.getStringExtra("data1")
            Log.d("test_intent", "$result") // 이거 왜 안되지
        }
    }*/


}