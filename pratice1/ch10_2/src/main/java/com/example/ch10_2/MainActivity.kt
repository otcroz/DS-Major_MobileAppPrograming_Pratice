package com.example.ch10_2

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ch10_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        // 뷰 바인딩
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RingtoneManager를 사용하여 소리 알림
        binding.button1.setOnClickListener {
            val nofication : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val rington = RingtoneManager.getRingtone(applicationContext, nofication) // Rington 객체를 구함
            rington.play()
        }

        // 앱에서 자체 음원을 사용하여 소리 알림
        binding.button2.setOnClickListener {
            val player : MediaPlayer = MediaPlayer.create(this, R.raw.funny_voices) // 리소스의 소리 넣기
            player.start()
        }

        binding.button3.setOnClickListener {
            // Vibrator 객체 획득
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){ // 버전에 따라 Vibrator 다르게 제꽁
                // VibratorManager을 사용하여 얻기
                val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            }
            else{
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }
            // Vibrator을 이용한 진동
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                //vibrate(진동이 몇 ms 동안 울릴 것인지, 진동의 세기), 진동의 세기는 API lv26부터 제공
                // ex: VibrationEffect.createWaveform(longArrayOf(500,1000,500,2000), intArrayOf(0,50,0,200), -1): 패턴대로 반복하여 울리기
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            }
            else{
                // vibrate(진동이 몇 ms 동안 울릴 것인지, 반복 횟수)
                vibrator.vibrate(500)
            }


        }
    }
}