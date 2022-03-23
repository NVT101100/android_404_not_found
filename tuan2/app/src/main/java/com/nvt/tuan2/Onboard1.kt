package com.nvt.tuan2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.nvt.tuan2.R.xml.*

class Onboard1 : AppCompatActivity() {
    lateinit var handler:Handler;
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard1);
        val board2:Intent = Intent(this,Onboard2::class.java);

        val top_ani:Animation = AnimationUtils.loadAnimation(this, top_animation);
        val bot_ani:Animation = AnimationUtils.loadAnimation(this, bot_animation);
        val top = findViewById<LinearLayout>(R.id.top1);
        val bot = findViewById<LinearLayout>(R.id.bot1);

        top.startAnimation(top_ani);
        bot.startAnimation(bot_ani);

        handler = Handler(Looper.getMainLooper());
        val runable : Runnable =object : Runnable{
            override fun run() {
                startActivity(board2);
            }
        }
        handler.postDelayed(runable,6000);
    }
}