package com.nvt.bloodbank.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.annotation.RequiresApi
import com.nvt.bloodbank.R

class Splash : AppCompatActivity() {
    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var handler: Handler
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        hideSystemUI()
        val mainActivity : Intent = Intent(this,Login::class.java);
        val imageView:ImageView = findViewById(R.id.load_image)
        imageView.apply {
            setBackgroundResource(R.drawable.spalsh_animation);
            animationDrawable = background as AnimationDrawable
        }
        animationDrawable.start();
        handler = Handler(Looper.getMainLooper());
        val runnable: Runnable = object :Runnable {
            override fun run() {
                finish();
                startActivity(mainActivity);
            }
        }
        handler.postDelayed(runnable,2000);
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())

            // When the screen is swiped up at the bottom
            // of the application, the navigationBar shall
            // appear for some time
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}