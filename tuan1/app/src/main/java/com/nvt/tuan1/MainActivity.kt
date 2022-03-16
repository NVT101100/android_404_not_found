package com.nvt.tuan1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wcLayout = Intent(this,Welcome::class.java);
        val lglayout = Intent(this,Singup::class.java);
        val vclayout = Intent(this,Verification::class.java);
        val boardlayout = Intent(this,Onboarding::class.java);
        val loginlayout = Intent(this,login::class.java)

        var btnWC : Button = findViewById(R.id.btnWelcome);
        var btnSU : Button = findViewById(R.id.btnSignup);
        var btnLG : Button = findViewById(R.id.btnLogin);
        var btnVC : Button = findViewById(R.id.btnVerification);
        var btnOB : Button = findViewById(R.id.btnBoard);

        btnWC.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(wcLayout);
            }
        })

        btnSU.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(lglayout);
            }
        })

        btnVC.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(vclayout);
            }
        })

        btnOB.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(boardlayout);
            }
        })

        btnLG.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(loginlayout);
            }
        })
    }
}