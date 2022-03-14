package com.nvt.tuan2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.nvt.tuan2.R.xml.top_animation

class Onboard3 : AppCompatActivity() {
    lateinit var handler:Handler
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboard3)
        val welcome: Intent = Intent(this,Welcome::class.java);

        val top_ani: Animation = AnimationUtils.loadAnimation(this, top_animation);
        val bot_ani: Animation = AnimationUtils.loadAnimation(this, R.xml.bot_animation);
        val top = findViewById<LinearLayout>(R.id.top3);
        val bot = findViewById<LinearLayout>(R.id.bot3);

        top.startAnimation(top_ani);
        bot.startAnimation(bot_ani);

        handler = Handler(Looper.getMainLooper());
        val runable : Runnable =object : Runnable{
            override fun run() {
                startActivity(welcome);
            }
        }
        handler.postDelayed(runable,6000);
    }
}