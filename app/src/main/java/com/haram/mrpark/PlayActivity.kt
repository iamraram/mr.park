package com.haram.mrpark

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class PlayActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val desc: MutableMap<Int, String> = mutableMapOf(
            1 to "갓 태어난 상태. 말랑말랑한 귀여운 볼이 인상적이다.",
            2 to "돌을 맞이한 박희찬, 과연 돌잡이로 무엇을 잡을까?",
            3 to "펜을 잡은 아이, 장래에 디자이너가 될건가보다.",
            3 to "키보드를 잡았다. 지금부터 개발자 생활 시작이다.",
        )

        val select: MutableList<String> = mutableListOf(
            "돌잡이 기회가 찾아왔습니다. 무엇을 잡으시겠습니까?",
            "중학교 입학 기회가 찾아왔습니다. 어디에 입학하시겠습니까?",
            "고등학교 입학 기회입니다. 어디에 원서를 내시겠습니까?",
            "수능을 망쳤습니다. 어떤 선택을 하시겠습니까?"
        )

        val percent: MutableList<Int> = mutableListOf(
            100, 98, 96, 94, 92, 90,
            85, 80, 75, 70, 65, 60,
            50, 40, 30, 20, 10, 9,
            8, 7, 6, 5, 4, 3, 2, 1
        )

        val img: MutableList<Int> = mutableListOf(
            R.drawable.lv1,
            R.drawable.lv2
        )

        var click:Int = 0

        val title = findViewById<TextView>(R.id.title)

        val descAct = findViewById<TextView>(R.id.desc)
        val perAct = findViewById<TextView>(R.id.per)
        val imgAct = findViewById<ImageView>(R.id.img)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            click =+ 1
            val random = Random()
            val num = random.nextInt(100)
            if (num > percent[click]) {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
                Toast.makeText(this, "강화에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.scale)
                imgAct.startAnimation(animation)

                title.text = "${click + 1}단계 박희찬"
                for (i in desc) {
                    descAct.text = i.value
                }
                perAct.text = "강화 확률 ${percent[click]}%"
                imgAct.setImageResource(img[click]);

            }
        }
    }
}