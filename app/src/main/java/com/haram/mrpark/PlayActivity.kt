package com.haram.mrpark

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

// 게임 데이터 가져오기
import com.haram.mrpark.GAME_DATA as datas

class PlayActivity : AppCompatActivity() {
    lateinit var title : TextView   // 레벨
    lateinit var descAct : TextView // 설명
    lateinit var count : TextView   // 추가 강화 확률
    lateinit var perAct : TextView  // 강화 확률
    lateinit var imgAct : ImageView // 이미지

    lateinit var btn : Button       // 강화 버튼

    var click: Int = 0       // 현재 레벨
    var addPercent: Int = 0 // 추가 강화 확률

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val select: MutableMap<Int, String> = mutableMapOf(
            9 to "선린에 입학했습니다!",
            12 to "에드캔에 들아왔습니다!"
        )

        title = findViewById(R.id.title)
        descAct = findViewById(R.id.desc)
        count = findViewById(R.id.tryCount)
        perAct = findViewById(R.id.per)
        imgAct = findViewById(R.id.img)

        btn = findViewById(R.id.btn)

        gameUiReset()

        btn.setOnClickListener {
            val random = Random()
            val num = random.nextInt(100)

            if (num > datas[click].nextLevelPercent + addPercent) {
                // 강화에 실패한 경우

                Toast.makeText(this, "강화에 실패했습니다.", Toast.LENGTH_SHORT).run {
                    // run 함수는 실행된 인자를 this로 사용할 수 있다
                    // kotlin에서 this는 생략이 가능하다 -> 더 깔끔한 코드를 작성 할 수 있다.
                    // toast.show() -> this.show() -> show() 으로 사용 가능

                    setGravity(Gravity.TOP, 0, 30)
                    show()
                }

                addPercent += 1
                perAct.text = "강화 확률 ${datas[click].nextLevelPercent}%"
                count.text = "추가 성공 확률 ${addPercent}%"

            } else {
                // 강화에 성공한 경우

                click += 1

                if (click == datas.size) { //todo 제한 조건 확인
                    val intent = Intent(this, FinishActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                for (i in select) {
                    if (click == i.key) {
                        count.text = i.value
                    } else {
                        count.text = "강화 성공!"
                    }
                }

                gameUiReset()
            }
        }
    }

    fun gameUiReset(){
        // 화면에 보여지는 ui의 내용을 수정하는 함수

        title.text = "${click + 1}단계 박희찬"
        descAct.text = datas[click].desc

        val animation: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.scale)
        imgAct.startAnimation(animation)
        imgAct.setImageResource(datas[click].image);

        perAct.text = "강화 확률 ${datas[click].nextLevelPercent}%"
        addPercent = 0
    }
}