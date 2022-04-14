package com.example.ch10application

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ch10.R
import com.example.ch10.databinding.ActivityMainBinding
import com.example.ch10.databinding.DialogInputBinding

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            //Toast.makeText(this, "첫 번째 버튼의 토스트입니다.", Toast.LENGTH_LONG).show() // 바로 실행하는 코드
            val toast = Toast.makeText(this, "첫 번째 버튼의 토스트입니다.", Toast.LENGTH_LONG)
            // 토스트에 대한 설정
            toast.setText("수정된 토스트입니다.")
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.TOP, 20, 20)
            toast.addCallback(
                @RequiresApi(Build.VERSION_CODES.R) // 레벨 호환성 오류 무시(무시하는 것 뿐 실행에 있어서는 변화X)
                object: Toast.Callback(){ // 버전 호환성(콜백 함수), 화면에 보이거나 사라지는 현상을 콜백으로 감지
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("mobileApp","토스트가 사라집니다.")
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("mobileApp","토스트가 나타납니다.")
                    }
                }
            )
            toast.show() // 토스트를 보여줌
        }

        //달력 띄우기 / month는 0부터 시작 ex) 1월 = 0
        binding.button2.setOnClickListener {
            DatePickerDialog(this,
                object:DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        Log.d("mobileApp", "$p1 년, ${p2 + 1} 월, $p3 일")
                    }
                },2022,3,30).show()
        }

        // 시간 설정
        binding.button3.setOnClickListener {
            TimePickerDialog(this,
                object:TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        Log.d("mobileApp", "$p1 시 $p2 분")
                    }
                }
                ,13,0,true).show()
        }

        // 다이얼로그 생성에 대한 이벤트 핸들러
        val eventHandler = object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if(p1 == DialogInterface.BUTTON_POSITIVE){
                    Log.d("mobileApp", "positive button")
                } else if(p1 == DialogInterface.BUTTON_NEGATIVE)
                    Log.d("mobileApp", "negative button")
            }
        }
        // 다이얼로그 띄우기
        binding.button4.setOnClickListener {
            //Builder를 사용하여 다이얼로그 생성 및 실행
            AlertDialog.Builder(this).run {
                setTitle("알림창 테스트")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("YES", eventHandler) // 이벤트 설정
                setNegativeButton("NO", eventHandler)
                setNeutralButton("MORE", null)
                setCancelable(false)
                show()
            }.setCanceledOnTouchOutside(false) // 다이얼로그 창 밖을 눌러도 종료되지 않도록
        } // 메시지 값 출력

        val items = arrayOf<String>("사과", "딸기", "수박" ,"토마토")
        binding.button5.setOnClickListener {
            //Builder를 사용하여 다이얼로그 생성 및 실행
            AlertDialog.Builder(this).run {
                setTitle("아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(items, object : DialogInterface.OnClickListener { // setFunc(내용, 이벤트 핸들러)
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Log.d("mobileApp", "${items[p1]}")
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.button6.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("멀티 아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)

                // 체크박스 설정 setMultiChoiceItems(내용, 초기값, 이벤트 핸들러)
                setMultiChoiceItems(items, booleanArrayOf(false, true, false, false),
                    object : DialogInterface.OnMultiChoiceClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                            Log.d("mobileApp", "${items[p1]} ${if(p2) "선택" else "해제"}")
                        }
                    }
                )

                setPositiveButton("닫기", null)
                show()
            }.setCanceledOnTouchOutside(false)
        }

        binding.button7.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("싱글 아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)

                // 라디오 버튼: setSingleChoiceItems(내용, 초기값, 이벤트 핸들러)
                setSingleChoiceItems(items, 1,
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            Log.d("mobileApp", "${items[p1]}")
                        }
                    })

                setPositiveButton("닫기", null)
                show()
            }
        }
    
        // 다이얼로그 레이아웃을 직접 만든 후 바인딩(뷰 바인딩 기법을 활용한 다이얼로그 만들기)
        val dialogBinding = DialogInputBinding.inflate(layoutInflater)
        val alert = AlertDialog.Builder(this)
            .setTitle("입력")
            .setView(dialogBinding.root)
            .setPositiveButton("닫기", null)
            .create()
        binding.button8.setOnClickListener {
            // 다이얼로그 실행
            AlertDialog.Builder(this).run {
                alert.show()
            }
        }

    }
}