package com.example.ch08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 터치 이벤트
    // rawX: 화면에서의 좌표값, x: 이벤트가 발생한 뷰에서의 좌표값
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("touch", "ACTION_DOWN x: ${event.x} rawX: ${event.rawX}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("touch", "ACTION_UP")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("touch", "ACTION_MOVE")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("keycode", "onKeyDown")
        when(keyCode){
            KeyEvent.KEYCODE_0 -> Log.d("keycode", "0 키를 눌렀네요")
            KeyEvent.KEYCODE_A -> Log.d("keycode", "A 키를 눌렀네요")
            KeyEvent.KEYCODE_BACK -> Log.d("keycode", "뒤로가기~")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("keycode", "볼륨 업!")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("keycode", "볼륨 다운!")
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("keycode", "onKeyUp")
        return super.onKeyUp(keyCode, event)
    }

    //뒤로가기:override fun onBackPressed()
}