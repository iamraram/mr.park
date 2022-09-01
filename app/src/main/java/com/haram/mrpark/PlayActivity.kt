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

class PlayActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val desc: MutableList<String> = mutableListOf(
            "갓 태어난 상태. 말랑말랑한 귀여운 볼이 인상적이다.",
            "체육관에 다니기 시작했다. 벌써부터 포스가 느껴진다.",
            "벌써부터 무술로 사람 한 명을 제압할 수 있다.",
            "꽃과 함께하는 아름다운 자태가 꽤나 매력적이다.",
            "1988 올림픽을 응원하는 캡틴 코리아 박희찬이다.",
            "부끄뎌운 희딴이 ver 1 - 매우 수줍어한다.",
            "부끄뎌운 희딴이 ver 2 - 깜찍한 포즈의 가운뎃손가락.",
            "부끄뎌운 희딴이 ver 3 - 새빨개진 얼굴로 입을 가린다.",
            "선린 입학 첫 날 미연시를 하는 희찬.",
            "애플파이에 들어가기를 희망하는 신입생 희찬.",
        )

        val select: MutableMap<Int, String> = mutableMapOf(
            9 to "선린에 입학했습니다!",
            12 to "에드캔에 들아왔습니다!"
        )

        val percent: MutableList<Int> = mutableListOf(
            100, 96, 92, 88, 84, 80, 75, 70, 65,
            60, 55, 50, 44, 38, 32, 26, 20, 10, 8,
            6, 4, 2, 1
        )

        val img: MutableList<Int> = mutableListOf(
            R.drawable.lv1,
            R.drawable.lv2,
            R.drawable.lv3,
            R.drawable.lv5,
            R.drawable.lv4,
            R.drawable.lv6,
            R.drawable.lv7,
            R.drawable.lv8,
            R.drawable.lv9,
            R.drawable.lv10,
            R.drawable.lv10,
            R.drawable.lv10,
            R.drawable.lv10,
            R.drawable.lv10,
            R.drawable.lv10,
        )

        var click:Int = 0

        val title = findViewById<TextView>(R.id.title)

        val descAct = findViewById<TextView>(R.id.desc)
        val count = findViewById<TextView>(R.id.tryCount)
        val perAct = findViewById<TextView>(R.id.per)
        val imgAct = findViewById<ImageView>(R.id.img)

        val btn = findViewById<Button>(R.id.btn)

        var temp: Int = 0

        btn.setOnClickListener {
            click += 1

            val random = Random()
            val num = random.nextInt(100)

            if (num > percent[click]) {
                val toast = Toast.makeText(this, "강화에 실패했습니다.", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 30)
                toast.show()

                percent[click] += 1
                temp += 1
                perAct.text = "강화 확률 ${percent[click]}%"
                count.text = "추가 성공 확률 ${temp}%"
                click -= 1
            }
            else {
                if (click == 10) {
                    val intent = Intent(this, FinishActivity::class.java)
                    startActivity(intent)
                }
                var txt:String = ""

                for (i in select) {
                    if (click == i.key) {
                        count.text = i.value
                    }
                    else {
                        count.text = "강화 성공!"
                    }
                }

                perAct.text = "강화 확률 ${percent[click + 1]}%"
                temp = 0

                val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.scale)
                imgAct.startAnimation(animation)

                title.text = "${click + 1}단계 박희찬"
                descAct.text = desc[click]
                imgAct.setImageResource(img[click]);
            }
        }
    }
}