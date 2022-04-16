package com.example.ch10_3_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.ch10_3_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        // 뷰 바인딩
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 1에서 onClick 이벤트 발생
        binding.button1.setOnClickListener{
            //1. 알림 빌더: 매니저와 빌더 생성
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder : NotificationCompat.Builder

            //호환성 처리: 26 버전이 넘을 때 채널 생성
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val ch_id = "one-channel"
                // NotificationChannel 생성: NotificationChannel(채널 식별자, 채널 이름, 중요도)
                val channel = NotificationChannel(
                    ch_id, "My Channel One",
                    NotificationManager.IMPORTANCE_DEFAULT) // 채널을 생성할 때 알림의 중요도 설정

                //channel 속성 변경
                channel.description ="My Channel One 소개" // 채널 설명 문자열
                channel.setShowBadge(true) // 홈 화면에 아이콘 배지 표시 여부
                channel.enableLights(true) // 불빛 표시 여부
                channel.lightColor = Color.RED
                channel.enableVibration(true) // 진동 여부
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200) // 진동 패턴
                // (100, 200) (100, 200) : (진동X 시간, 진동 시간) : msec

                //소리 알림
                val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audio_attr = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                channel.setSound(uri, audio_attr) // 알림음 재생

                // 채널을 NotificationManager 객체에 등록
                manager.createNotificationChannel(channel)
                // 채널을 이용하여 빌더 생성
                builder = NotificationCompat.Builder(this,ch_id) // 채널의 식별값 지정 => 앱의 알림을 채널로 구분
            } else{ // 버전 26이 넘지 않을 때
                builder = NotificationCompat.Builder(this)
            }

            //2. 알림의 스타일 설정: 알림 객체 생성
            builder.setSmallIcon(R.drawable.small)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("안녕하세요")
            builder.setContentText("모바일앱프로그래밍 시간입니다.")
            val bigPic = BitmapFactory.decodeResource(resources, R.drawable.big)
            val buildStyle = NotificationCompat.BigPictureStyle()
            buildStyle.bigPicture((bigPic))

            builder.setStyle(buildStyle) // 비트맵(리소스) 관련 스타일 적용

            // 3. 액션 설정
            //인텐트 생성 1
            val replyIntent = Intent(this, ReplyReceiver::class.java)

            // 인텐트 설정 2
            // getBroadcast(context, 적절한 숫자, 등록한 인텐트, 내용 변경 여부)
            val replyPendingIntent = PendingIntent.getBroadcast(this, 30, replyIntent, PendingIntent.FLAG_MUTABLE) // 해당 내용 변경 여부: FLAG_MUTABLE로 설정해야한다.
            builder.setContentIntent(replyPendingIntent) //인텐트 등록,
            // 위의 코드를 주석처리하면 알림을 터치하는 것에 대한 인텐트 처리가 발생하지 X

            // 원격으로 입력에 대한 액션: 인텐트
            val remoteInput = RemoteInput.Builder("key_text_reply").run{ // RemoteInput은 안드로이드와 안드로이드X에서 제공한다.
                setLabel("답장")
                build()
            }

            // 알림에 대한 Action: 알림 띄우기 - 최대 3개까지
            builder.addAction( // 액션 1
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "Action",
                    replyPendingIntent //replyReceiver가 실행
                ).build() // 액션 생성
            )

            builder.addAction( // 액션 2
                NotificationCompat.Action.Builder(
                    R.drawable.send,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build() // 액션 실행 시 원격으로 답장을 함 //@ 현재 주어진 것에서 변경이 일어날 수 있음
            )

            manager.notify(11, builder.build()) // builder.build()가 Notification 객체 생성
            
            // 알림 취소
            // manager.cancel(11) // 11은 위의 notify에서 임의로 설정한 값
            // 알림 취소 막기
            // builder.setAutoCancel() 자동으로 삭제 막음
            // builder.setOngoing() 알림이 사라지지 않고 계속 실행
        }
    }
}