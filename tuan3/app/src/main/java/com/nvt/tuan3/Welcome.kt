package com.nvt.tuan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
        val skip:Button = findViewById(R.id.btnSkip);
        val signin:TextView = findViewById(R.id.txtSignin);
        val acSignup:Intent = Intent(this,Signup::class.java)
        val acSigin:Intent = Intent(this,Login::class.java)

        skip.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(acSignup);
            }
        })
        signin.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(acSigin);
            }
        })
    }
}