package com.example.ch08

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.example.ch08.databinding.ActivityVieweventBinding

class ViewEventActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener { //2-1 액티비티 자체에서 이벤트 인터페이스 구현
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityVieweventBinding.inflate(layoutInflater)
        // 1. object 클래스를 핸들러로 만들어서 이벤트 처리
        binding.checkBox1.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                Log.d("ckecked test" ,"체크박스 클릭")
            }
        })

        // 2-2 이벤트 핸들러 호출
        binding.checkBox1.setOnCheckedChangeListener(this)


        // 4. SAM 기법**
        val binding1 = ActivityVieweventBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        binding1.checkBox1.setOnCheckedChangeListener{
            CompoundButton, b -> Log.d("ckecked test" ,"체크박스 클릭")
        }

        // 버튼의 클릭, 롱클릭 이벤트 처리
        binding.checkBox1.setOnClickListener{
            Log.d("clicked test" ,"클릭")
        }
        binding.checkBox1.setOnLongClickListener{
            Log.d("clicked test" ,"길게 클릭")
            true
        }
    }

    // 2-3, 3-2 이벤트 함수 호출
    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        Log.d("ckecked test" ,"체크박스 클릭")
    }
}

// 3-1 별도의 이벤트 핸들러 클래스 생성
class MyEventHandler : CompoundButton.OnCheckedChangeListener{
    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        Log.d("ckecked test" ,"체크박스 클릭")
    }
}