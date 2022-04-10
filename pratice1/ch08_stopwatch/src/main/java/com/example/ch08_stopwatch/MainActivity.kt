package com.example.ch08_stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.ch08_stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var pauseTime = 0L // 정지했을 때 시간, 음수값을 가진다
    var initTime = 0L //


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener { // 시작 버튼을 눌렀을 때
            Log.d("timetest", "시작: ${SystemClock.elapsedRealtime()}, ${binding.chronometer.base}, $pauseTime")
            binding.chronometer.base = SystemClock.elapsedRealtime()  + pauseTime // 기준 시간
            binding.chronometer.start()
            binding.buttonStart.isEnabled = false
            binding.buttonStop.isEnabled = true
            binding.buttonReset.isEnabled = true
        }

        binding.buttonStop.setOnClickListener { // 정지 버튼을 눌렀을 때
            // stop 버튼을 눌렀을 때의 시간(음수)

            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime() // 베이스 값 - 실제로 지난 시간
            Log.d("time test", "정지: ${SystemClock.elapsedRealtime()}, ${binding.chronometer.base}, $pauseTime")
            binding.chronometer.stop()
            binding.buttonStart.isEnabled = true
            binding.buttonStop.isEnabled = false
            binding.buttonReset.isEnabled = true
        }

        binding.buttonReset.setOnClickListener { // 리셋 버튼을 눌렀을 때
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.buttonStart.isEnabled = true
            binding.buttonStop.isEnabled = true
            binding.buttonReset.isEnabled = false
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-initTime > 3000){ // 클릭 후 3초 내에 또 클릭했을 때 종료하도록
                         Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_LONG).show()
                initTime = System.currentTimeMillis() // 시스템의 현재 시간을 받음
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }


}